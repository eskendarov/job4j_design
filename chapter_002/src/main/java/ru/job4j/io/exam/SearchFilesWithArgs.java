package ru.job4j.io.exam;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Программа для поиска файлов по критериям.
 * Запуск приложения с консоли имеет вид:
 * java -jar find.jar -d=c:/ -n=*.txt -t=mask -o=log.txt
 * -d - директория, с которой начинается поиск, включая поддиректории;
 * -n - имя файла, маска, либо регулярное выражение;
 * -t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению;
 * -o - имя файла, в который запишется результат.
 * (!) Аргументы с пробелами  необходимо заключить в кавычки ("some file.txt"),
 * чтобы интерпретировать как один аргумент.
 *
 * @author Enver Eskendarov
 */
public class SearchFilesWithArgs {

    private static Path ROOT;
    private static String EXPRESSION;
    private static String SEARCH_TYPE;
    private static String OUTPUT;
    private final static String MESSAGE = String.format(
            " Please input valid args, for example: %s (%s)",
            "-d=c:\\-n=*.txt -t=mask -o=log.txt",
            "d: root, n: expression, t: {filename, regex, mask}, o: outputFile "
    );

    private static boolean isValid(String[] args) {
        final Map<String, String> map = Stream.of(args)
                .filter(arg -> arg.matches("^-+[dnto]+=+.*"))
                .collect(Collectors.toMap(
                        key -> key.split("=")[0],
                        value -> value.split("=")[1]));
        if (map.size() == 4) {
            ROOT = Paths.get(map.get("-d"));
            EXPRESSION = map.get("-n");
            SEARCH_TYPE = map.get("-t");
            OUTPUT = map.get("-o");
            return true;
        }
        return false;
    }

    private static List<Path> search() throws IOException {
        if (!Files.exists(ROOT)) {
            throwException(ROOT + " - Invalid search directory.");
        }
        final FileVisitor visitor = new FileVisitor(SEARCH_TYPE, EXPRESSION);
        Files.walkFileTree(ROOT, visitor);
        return visitor.getResultList();
    }

    static void throwException(String warning) {
        throw new UnsupportedOperationException(warning + MESSAGE);
    }

    public static void main(String[] args) throws IOException {
        if (!isValid(args)) {
            throwException("Invalid args.");
        }
        final List<Path> pathList = search();
        if (pathList.isEmpty()) {
            System.out.println("Files not found!");
        }
        try (FileWriter fileWriter = new FileWriter(OUTPUT)) {
            for (Path path : pathList) {
                System.out.println(path);
                fileWriter.write(path + System.lineSeparator());
            }
        }
    }
}
