package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        for (String arg : args) {
            final String[] keyValue = arg.split("=");
            final String key = keyValue[0].split("-", 2)[1];
            final String value = keyValue[1];
            values.put(key, value);
        }
    }

    public static ArgsName of(String[] args) {
        final ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        final ArgsName jvm = ArgsName.of(
                new String[]{"-Xmx=512", "-encoding=UTF-8"}
        );
        System.out.println(jvm.get("Xmx"));
        final ArgsName zip = ArgsName.of(
                new String[]{"-out=project.zip", "-encoding=UTF-8"}
        );
        System.out.println(zip.get("out"));
    }
}
