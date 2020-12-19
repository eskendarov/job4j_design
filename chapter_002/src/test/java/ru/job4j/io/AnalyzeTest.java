package ru.job4j.io;

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;

public class AnalyzeTest {

    /**
     * source.log:
     * 200 10:56:01
     * 500 10:57:01
     * 400 10:58:01
     * 200 10:59:01
     * 300 11:00:03
     * 500 11:01:02
     * 200 11:02:02
     */
    @Test
    public void unavailableDiapasons() {
        final String source = "./data/source.log";
        final String target = "./data/target.log";
        new Analyze().unavailable(source, target);
        try (BufferedReader read = new BufferedReader(new FileReader(target))) {
            final List<String> list = read.lines().collect(Collectors.toList());
            Assert.assertThat(list, is(
                    Arrays.asList("10:57:01;10:59:01;", "11:01:02;11:02:02;")
            ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
