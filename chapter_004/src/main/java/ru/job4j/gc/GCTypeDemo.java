package ru.job4j.gc;

import java.util.Random;

/**
 * <p>
 * Project: Garbage Collector
 * Date: 10.09.2021
 * Time: 5:28
 * <p>
 * GCTypeDemo - Class
 *
 * @author Enver Eskendarov
 * @version 1.0
 */

public class GCTypeDemo {

    public static void main(String[] args) {
        Random random = new Random();
        int length = 100;
        String[] data = new String[1_000_000];
        for (int i = 0; ; i = (i + 1) % data.length) {
            data[i] = String.valueOf(
                    (char) random.nextInt(255)
            ).repeat(length);
        }
    }
}