package ru.job4j.io;

import java.nio.file.Path;
import java.util.HashMap;

public class ArgZip {

    private final HashMap<String, String> keyVal = new HashMap<>();

    public ArgZip(String[] args) {
        for (String arg : args) {
            final String[] keyValue = arg.split("=");
            final String key = keyValue[0].split("-", 2)[1];
            final String value = keyValue[1];
            keyVal.put(key, value);
        }
    }

    public boolean valid() {
        return keyVal.size() == 3;
    }

    public String dir() {
        return keyVal.get("d");
    }

    public String exc() {
        return keyVal.get("e");
    }

    public String out() {
        return keyVal.get("o");
    }
}
