package ru.job4j.exam;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.exam.Analyze.Info;
import ru.job4j.exam.Analyze.User;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;

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
        Info info = new Analyze().diff(previous, current);
        Assert.assertThat(info, is(new Info(3, 2, 3)));
    }
}
