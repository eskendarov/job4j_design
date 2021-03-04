package ru.job4j.io.exam;

import java.nio.file.Path;

/**
 * The class {@code Shell} simulates a Linux terminal.
 *
 * @author Enver Eskendarov
 * @version 1.0 03/03/2021
 */
public class Shell {


    private final StringBuilder path = new StringBuilder();

    /**
     * @param cmd The Linux commands.
     */
    public void cd(String cmd) {
        path.append(cmd.startsWith("/") ? cmd : "/" + cmd);
    }

    /**
     * Method returns path working directory.
     */
    public String pwd() {
        return Path.of(path.toString()).normalize().toString()
                .replace("\\", "/");
    }
}
