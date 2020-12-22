package ru.job4j.io;

import java.io.File;

public class Dir {

    public static void main(String[] args) {
        final File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(
                    String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(
                    String.format("Not directory %s", file.getAbsoluteFile()));
        }
        for (File subFile : file.listFiles()) {
            System.out.println(String.format("file: %s - size: %s",
                    subFile.getName(),
                    subFile.getTotalSpace())
            );
        }
    }
}
