package ru.job4j.io.exam;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ShellTest {

    @Test
    public void whenCdBack() {
        final Shell shell = new Shell();
        shell.cd("/user/..");
        assertThat(shell.pwd(), is("/"));
    }

    @Test
    public void whenCdRoot() {
        final Shell shell = new Shell();
        shell.cd("/");
        assertThat(shell.pwd(), is("/"));
    }

    @Test
    public void whenCdUserLocal() {
        final Shell shell = new Shell();
        shell.cd("user");
        shell.cd("local");
        assertThat(shell.pwd(), is("/user/local"));
    }

    @Test
    public void whenCdUserBack() {
        final Shell shell = new Shell();
        shell.cd("user");
        shell.cd("..");
        assertThat(shell.pwd(), is("/"));
    }
}
