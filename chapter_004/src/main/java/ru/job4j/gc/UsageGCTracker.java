package ru.job4j.gc;

/**
 * Date: 16.09.2021
 * Project: tracker
 * Class: UsageGCForTracker
 * Оценка времени работы разных GC при создании 9999999 объектов без ссылок:
 * <p>
 * |____GS____|______VM Option______|___Количество запусков программы___|
 * |   Serial | -XX:+UseSerialGC    | 5.742s | 5.548s | 5.827s | 5.098s |
 * | Parallel | -XX:+UseParallelGC  | 5.685s | 5.595s | 6.098s | 5.767s |
 * |       G1 | -XX:+UseG1GC        | 4.718s | 5.218s | 4.583s | 4.771s |
 * |      ZGC | -XX:+UseZGC         | 3.965s | 3.986s | 4.308s | 4.143s |
 * <p>
 * Для анализа используем VM Options для логирования GC:
 * Вывод в консоль: -Xlog:gc*=debug:stdout
 * Запись в файл:   -Xlog:gc*=debug:file=gs.log
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
public class UsageGCTracker {

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
        for (int index = 0; index < 9999999; index++) {
            new User("Object", index);
        }
        info();
    }
}
