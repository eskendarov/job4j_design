package ru.job4j.io.exam;

import java.nio.file.Path;

/**
 * The class {@code Shell} simulates a Linux terminal.
 *
 * @author Enver Eskendarov
 * @version 1.0 03/03/2021
 */
public final class Shell {

    /**
     * Path builder for Linux command.
     */
    private final StringBuilder path = new StringBuilder();

    /**
     * @param cmd the Linux commands.
     */
    public void cd(final String cmd) {
        path.append(cmd.startsWith("/") ? "" : "/").append(cmd);
    }

    /**
     * @return path working directory.
     */
    public String pwd() {
        return Path.of(path.toString()).normalize().toString()
                .replace("\\", "/");
    }
}
