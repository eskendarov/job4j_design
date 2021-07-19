package ru.job4j.io.scanner;

import ru.job4j.io.ArgsName;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Класс CSVReader, читает данные из CSV файла и выводит их.
 * Аргументы для запуска программы:
 * - path - путь к файлу с которого считываются данные
 * - delimiter - разделитель столбцов CSV
 * - out - тип вывода в консоль 'stdout' или в файл 'data.txt'
 * - filter - фильтр по столбцам
 * Например запуск программы для файла CSV со столбцами:
 * name, age, birthDate, education, children
 * -path=file.txt -delimiter=";"  -out=stdout -filter=name,age.
 *
 * @author Enver Eskendarov
 * @version 1.0 17.07.2021
 */

public class CSVReader {

    private final Path path;
    private final String delimiter;
    private final String out;
    private final StringBuilder builder = new StringBuilder();
    private final List<String> filter;

    /**
     * @param path      - путь к файлу с которого считываются данные
     * @param delimiter - разделитель столбцов CSV
     * @param out       - тип вывода в консоль 'stdout' или в файл 'data.txt'
     * @param filter    - фильтр по столбцам
     */
    public CSVReader(Path path,
                     String delimiter,
                     String out,
                     String[] filter) {
        this.path = path;
        this.delimiter = delimiter;
        this.out = out;
        this.filter = List.of(filter);
    }

    public void read() throws IOException {
        final Scanner scanFile = new Scanner(path)
                .useDelimiter(System.lineSeparator());
        // Считываем имена столбцов, для разделения и индексации
        final String[] colNames = scanFile.next().split(delimiter);
        final int[] index = IntStream.range(0, colNames.length)
                .filter(i -> filter.contains(colNames[i])).toArray();
        // Считываем строки по фильтрам и записываем в буфер
        while (scanFile.hasNextLine()) {
            final String[] columns = scanFile.next().split(delimiter);
            for (int i = 0; i < index.length; i++) {
                builder.append(String.format("%s='%s' ",
                        filter.get(i), columns[index[i]]
                ));
            }
            builder.append(System.lineSeparator());
        }
        scanFile.close();
    }

    public void printInfo() throws IOException {
        if (out.equals("stdout")) {
            System.out.println(builder);
        } else {
            try (FileWriter writer = new FileWriter(out)) {
                writer.write(builder.toString());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        final ArgsName argsName = ArgsName.of(args);
        final Path path = Path.of(argsName.get("path"));
        // Проверяем аргументы (5) и читаемость файла.
        if (args.length != 4 || !path.toFile().isFile()) {
            throw new IllegalArgumentException("Invalid path: " + path);
        }
        final CSVReader reader = new CSVReader(
                path,
                argsName.get("delimiter"),
                argsName.get("out"),
                argsName.get("filter").split(",")
        );
        reader.read();
        reader.printInfo();
    }
}
