package ru.job4j.io.exam;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * @author Enver Eskendarov
 */
public class FileVisitor extends SimpleFileVisitor<Path> {

    private final String searchType;
    private final String expression;
    private final ArrayList<Path> list = new ArrayList<>();

    public FileVisitor(String searchType, String expression) {
        this.searchType = searchType;
        this.expression = expression;
    }

    public ArrayList<Path> getResultList() {
        return list;
    }

    private void add(Predicate<String> isValid, Path path) {
        if (isValid.test(path.toFile().getName()) && path.toFile().isFile()) {
            list.add(path);
        }
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
        final Predicate<String> valid = fileName -> {
            switch (searchType) {
                case "name" -> {
                    return fileName.equalsIgnoreCase(expression);
                }
                case "regex" -> {
                    return Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
                            .matcher(fileName).find();
                }
                case "mask" -> {
                    final String[] lr = expression.split("\\.");
                    return fileName.matches(String.format("^%s+.+%s", lr[0], lr[1]));
                }
                default -> {
                    SearchFilesWithArgs.throwException("Invalid search type!");
                    return false;
                }
            }
        };
        add(valid, path);
        return FileVisitResult.CONTINUE;
    }
}
