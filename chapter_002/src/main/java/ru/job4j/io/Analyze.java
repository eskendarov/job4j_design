package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.System.lineSeparator;

public class Analyze {

    private boolean isStarted;
    private final StringBuilder builder = new StringBuilder();

    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
                List<String[]> list = read.lines()
                        .filter(line -> !line.startsWith("#") && !line.isBlank())
                        .map(line -> line.split(" ", 2))
                        .collect(Collectors.toList());
                for (String[] type : list) {
                    switch (type[0]) { // String[] type = {status, date}
                        case "400":
                        case "500": {
                            if (!isStarted) {
                                builder.append(type[1]).append(";");
                                isStarted = true;
                            }
                            break;
                        }
                        default: {
                            if (isStarted) {
                                builder.append(type[1]).append(";").append(lineSeparator());
                                isStarted = false;
                            }
                            break;
                        }
                    }
                }
                out.print(builder.toString());
            }
        } catch (java.io.IOException | IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
