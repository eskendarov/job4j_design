package ru.job4j.tdd;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class MaxMinTest extends MaxMin {

    final List<Integer> list = List.of(6, 3, 6, 7, 4, 3, -1, 4);

    @Test
    public void testMax() {
        Assert.assertThat(7, is(max(list, Integer::compareTo)));
    }

    @Test
    public void testMin() {
        Assert.assertThat(-1, is(min(list, Integer::compareTo)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmpty() {
        max(Collections.emptyList(), Integer::compareTo);
    }
}
