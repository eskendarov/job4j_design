package ru.job4j.io;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;

public class AnalyzeTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void unavailableDiapasons() throws IOException {
        final File source = folder.newFile("source.log");
        final File target = folder.newFile("target.log");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("300 11:00:03");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        new Analyze().unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        final List<String> targetList;
        try (BufferedReader read = new BufferedReader(new FileReader(target))) {
            targetList = read.lines().collect(Collectors.toList());

        }
        Assert.assertThat(targetList, is(
                Arrays.asList("10:57:01;10:59:01;", "11:01:02;11:02:02;")
        ));
    }
}
