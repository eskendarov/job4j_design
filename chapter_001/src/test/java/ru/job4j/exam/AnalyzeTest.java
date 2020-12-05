package ru.job4j.exam;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.exam.Analyze.Info;
import ru.job4j.exam.Analyze.User;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class AnalyzeTest {

    @Test
    public void whenAddTwo() {
        final List<User> previous = List.of(
                new User(1, "Jimmy"),
                new User(2, "Adam"),
                new User(3, "Olesya"));
        final List<User> current = List.of(
                new User(1, "Jimmy"),
                new User(2, "Adam"),
                new User(3, "Olesya"),
                new User(7, "Enver"),
                new User(4, "Sara"));
        final Info info = new Analyze().diff(previous, current);
        Assert.assertThat(info, is(new Info(2, 0, 0)));
    }

    @Test
    public void whenChangeAll() {
        final List<User> previous = List.of(
                new User(1, "Jimmy"),
                new User(2, "Adam"),
                new User(3, "Olesya"));
        final List<User> current = List.of(
                new User(1, "Enver"),
                new User(2, "Eric"),
                new User(3, "Sonya"));
        final Info info = new Analyze().diff(previous, current);
        Assert.assertThat(info, is(new Info(0, 3, 0)));
    }

    @Test
    public void whenDelAllAndAddTwo() {
        final List<User> previous = List.of(
                new User(1, "Jimmy"),
                new User(2, "Adam"),
                new User(4, "Sara"));
        final List<User> current = List.of(
                new User(5, "Robert"),
                new User(7, "Lev"));
        final Info info = new Analyze().diff(previous, current);
        Assert.assertThat(info, is(new Info(2, 0, 3)));
    }
}
