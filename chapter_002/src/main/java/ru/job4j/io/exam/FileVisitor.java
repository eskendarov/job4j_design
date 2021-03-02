package ru.job4j.io.exam;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * @author Enver Eskendarov
 */
public class FileVisitor extends SimpleFileVisitor<Path> {

    private final Predicate<Path> validate;
    private final ArrayList<Path> list = new ArrayList<>();

    public FileVisitor(Predicate<Path> validate) {
        this.validate = validate;
    }

    public ArrayList<Path> getResultList() {
        return list;
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
        if (validate.test(path) && attrs.isRegularFile()) {
            list.add(path);
        }
        return FileVisitResult.CONTINUE;
    }
}
