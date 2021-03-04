package ru.job4j.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(ArgZip zip) throws IOException {
        if (!zip.valid()) {
            throw new IllegalArgumentException();
        }
        final Path root = Path.of(zip.dir());
        try (FileOutputStream fos = new FileOutputStream(zip.out());
             ZipOutputStream zipOut = new ZipOutputStream(fos)
        ) {
            Files.walk(root)
                    .filter(path -> !path.toFile().isDirectory())
                    .filter(path -> !path.toFile().getName()
                            .endsWith(zip.exc()))
                    .forEach(path -> {
                        try {
                            zipOut.putNextEntry(
                                    new ZipEntry(root.relativize(path)
                                            .toString())
                            );
                            Files.copy(path, zipOut);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        }
    }

    public void packSingleFile(File source, File target) {
        try (FileOutputStream fos = new FileOutputStream(target);
             ZipOutputStream zip = new ZipOutputStream(fos)
        ) {
            zip.putNextEntry(new ZipEntry(source.getName()));
            Files.copy(Path.of(source.toURI()), zip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Zip().packSingleFile(
                new File("pom.xml"),
                new File("pom.zip")
        );
        new Zip().packFiles(new ArgZip(args));
    }
}
