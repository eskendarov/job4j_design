package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.function.Consumer;

import static java.lang.System.lineSeparator;

public class Analyze {

    private boolean isStarted;
    private final StringBuilder builder = new StringBuilder();
    private final Consumer<String[]> log = type -> {
        switch (type[0]) { // String[] type = {status, date}
            case "400", "500" -> {
                if (!isStarted) {
                    builder.append(type[1]).append(";");
                    isStarted = true;
                }
            }
            default -> {
                if (isStarted) {
                    builder.append(type[1]).append(";").append(lineSeparator());
                    isStarted = false;
                }
            }
        }
    };

    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
                read.lines()
                        .filter(line -> !line.startsWith("#") && !line.isBlank())
                        .map(line -> line.split(" ", 2))
                        .forEach(log);
                out.print(builder.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
