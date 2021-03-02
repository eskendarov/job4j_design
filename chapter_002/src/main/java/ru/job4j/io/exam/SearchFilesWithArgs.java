package ru.job4j.io.exam;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.regex.Pattern;
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
    private static File OUTPUT_FILE;
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
            OUTPUT_FILE = new File(map.get("-o"));
            return true;
        }
        return false;
    }

    private static List<Path> search() throws IOException {
        if (!Files.exists(ROOT)) {
            throwException(ROOT + " - Invalid search directory.");
        }
        final Predicate<Path> validate = path -> {
            final Predicate<String> validType = pattern -> Pattern.compile
                    ("(?i)" + pattern).matcher(path.toFile().getName()).find();
            switch (SEARCH_TYPE) {
                case "name", "mask" -> {
                    return validType.test("^" + EXPRESSION + "$");
                }
                case "regex" -> {
                    return validType.test(EXPRESSION);
                }
                default -> {
                    throwException("Invalid search type!");
                    return false;
                }
            }
        };
        final FileVisitor visitor = new FileVisitor(validate);
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
        } else {
            try (FileWriter fileWriter = new FileWriter(OUTPUT_FILE)) {
                for (Path path : pathList) {
                    fileWriter.write(path + System.lineSeparator());
                }
            }
            Desktop.getDesktop().open(OUTPUT_FILE); // Вывод файла на монитор.
        }
    }
}
