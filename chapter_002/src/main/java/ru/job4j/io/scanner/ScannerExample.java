package ru.job4j.io.scanner;

import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.util.Scanner;

/**
 * ScannerExample Class - description goes here.
 *
 * @author Enver Eskendarov
 * @version 1.0 17.07.2021
 */
public class ScannerExample {

    public static void main(String[] args) {
        var ls = System.lineSeparator();
        var data = String.join(ls,
                "1 2 3", "4 5 6", "7 8 9"
        );
        var scanner = new Scanner(new CharArrayReader(data.toCharArray()));
        while (scanner.hasNextInt()) {
            System.out.print(scanner.nextInt());
            System.out.print(" ");
        }
        System.out.println();
        data = "empl1@mail.ru, empl2@mail.ru, empl3@mail.ru";
        scanner = new Scanner(new ByteArrayInputStream(data.getBytes()))
                .useDelimiter(", ");
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
    }
}
