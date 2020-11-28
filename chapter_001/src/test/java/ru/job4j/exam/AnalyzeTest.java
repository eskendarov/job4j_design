package ru.job4j.exam;

import org.junit.Test;
import ru.job4j.exam.Analyze.Info;
import ru.job4j.exam.Analyze.User;

import java.util.List;

public class AnalyzeTest {

    @Test
    public void test() {
        List<User> previous = List.of(
                new User(1, "Jimmy"),
                new User(2, "Adam"),
                new User(3, "Olesya"),
                new User(4, "Sara")
        );
        List<User> current = List.of(
                new User(1, "Robert"),
                new User(2, "Lev"),
                new User(5, "Lisa"),
                new User(6, "Oleg"),
                new User(7, "Enver"),
                new User(4, "Sara")
        );
        Analyze analyze = new Analyze();
        Info info = analyze.diff(previous, current);
        System.out.println(info.toString());
    }
}
