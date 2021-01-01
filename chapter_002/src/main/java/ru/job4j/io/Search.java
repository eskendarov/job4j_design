package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Search {

    public static void main(String[] args) throws IOException {
        final Path start = Paths.get(".");
        final Path root = Paths.get("C:\\Users\\EEA\\Downloads");
        search(start, "java").forEach(System.out::println);
        searchDuplicates(root, new SearchDuplicates()).forEach(System.out::println);
    }

    private static List<File>
    searchDuplicates(Path root, SearchDuplicates duplicates) throws IOException {
        Files.walkFileTree(root, duplicates);
        return duplicates.getDuplicates();
    }

    public static List<Path> search(Path root, String ext) throws IOException {
        final SearchFiles searcher =
                new SearchFiles(p -> p.toFile().getName().endsWith(ext));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
