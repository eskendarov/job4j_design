package ru.job4j.io.exam;

import java.awt.Desktop;
import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Программа для поиска файлов по критериям.
 * Запуск приложения с консоли имеет вид:
 * java -jar find.jar -d=c:/ -n=*.txt -t=mask -o=log.txt
 * -d - директория, с которой начинается поиск, включая поддиректории;
 * -n - имя файла, маска, либо регулярное выражение;
 * -t - тип поиска: mask искать по маске, name по полному совпадение имени,
 * regex по регулярному выражению;
 * -o - имя файла, в который запишется результат.
 * (!) Аргументы с пробелами  необходимо заключить в кавычки ("some file.txt"),
 * чтобы интерпретировать как один аргумент.
 *
 * @author Enver Eskendarov
 */
public class SearchFilesWithArgs {

    private static Path root;
    private static String expression;
    private static String searchType;
    private static File outputFile;
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
            root = Paths.get(map.get("-d"));
            expression = map.get("-n");
            searchType = map.get("-t");
            outputFile = new File(map.get("-o"));
            return true;
        }
        return false;
    }

    private static boolean test(String expression, String file) {
        return Pattern.compile("(?i)" + expression).matcher(file).find();
    }

    private static boolean check(Path path) {
        final String file = path.toFile().getName();
        if (List.of("name", "mask").contains(searchType)) {
            return test("^" + expression + "$", file);
        } else if ("regex".equals(searchType)) {
            return test(expression, file);
        }
        throwException("Invalid search type!");
        return false;
    }

    private static List<Path> search() throws IOException {
        if (!Files.exists(root)) {
            throwException(root + " - Invalid search directory.");
        }
        final FileVisitor visitor = new FileVisitor(path -> {
            try {
                return check(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        });
        Files.walkFileTree(root, visitor);
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
            try (FileWriter fileWriter = new FileWriter(outputFile)) {
                for (Path path : pathList) {
                    fileWriter.write(path + System.lineSeparator());
                }
            }
            Desktop.getDesktop().open(outputFile);
        }
    }
}
