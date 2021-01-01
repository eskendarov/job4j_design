package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchDuplicates implements FileVisitor<Path> {

    final private Set<FileProperties> set = new HashSet<>();
    final private List<File> duplicates = new ArrayList<>();

    public List<File> getDuplicates() {
        return duplicates;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        final FileProperties fileProp = new FileProperties(file.toFile());
        if (!set.add(fileProp)) {
            duplicates.add(fileProp.getFile());
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        return CONTINUE;
    }
}
