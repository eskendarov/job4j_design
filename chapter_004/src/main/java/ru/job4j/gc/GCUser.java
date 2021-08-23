package ru.job4j.gc;

/**
 * Запуск с ключами: -Xms4m -Xmx4m (64x - JVM)
 *
 * @author Enver Eskendarov
 * @version 1.0 23.08.2021
 */
public class GCUser {

    private static final long KB = 1024;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long userMemory = totalMemory - freeMemory;
        System.out.println("=== Environment state ===");
        System.out.printf("freeMemory: %d%n", freeMemory / MB);
        System.out.printf("totalMemory: %d%n", totalMemory / MB);
        System.out.printf("userMemory: %d%n", userMemory / MB);
    }

    public static void main(String[] args) {
        info();
        for (int index = 0; index < 18000; index++) {
            new User("a", index);
        }
        info();
    }
}
