package ru.job4j.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {

    private final String logPath;
    private final String botAnswers;
    private final String OUT = "закончить";
    private final String STOP = "стоп";
    private final String CONTINUE = "продолжить";
    private boolean isStopped = false;

    public ConsoleChat(String logPath, String botAnswers) {
        this.logPath = logPath;
        this.botAnswers = botAnswers;
    }

    private List<String> getAnswers() {
        final List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
            String line = in.readLine();
            while (line != null) {
                rsl.add(line);
                line = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void log(List<String> data) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(
                logPath, Charset.forName("WINDOWS-1251"), true)
        )) {
            for (String s : data) {
                out.write(s);
                out.write(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void chatStatus(String userCommand) {
        if (userCommand.equals(STOP)) {
            isStopped = true;
        }
        if (userCommand.equals(CONTINUE)) {
            isStopped = false;
        }
    }

    public void run() {
        final Random random = new Random();
        final List<String> botAnswers = getAnswers();
        final List<String> logsList = new ArrayList<>();
        final Scanner scanner = new Scanner(System.in);
        String userCommand = scanner.nextLine();
        while (!userCommand.equals(OUT)) {
            logsList.add("User: " + userCommand);
            final String botAnswer
                    = botAnswers.get(random.nextInt(botAnswers.size()));
            chatStatus(userCommand);
            if (!isStopped) {
                System.out.println(botAnswer);
                logsList.add("Bot: " + botAnswer);
            }
            userCommand = scanner.nextLine();
        }
        logsList.add("User: " + userCommand);
        log(logsList);
    }

    public static void main(String[] args) {
        final ConsoleChat cc = new ConsoleChat("logChat.txt", "answer.txt");
        cc.run();
    }
}
