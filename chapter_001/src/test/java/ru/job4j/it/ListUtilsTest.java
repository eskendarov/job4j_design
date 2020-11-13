package ru.job4j.it;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void addBefore() {
        final List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        final List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void addAfter() {
        final List<Integer> input = new ArrayList<>(Arrays.asList(1, 5, 3));
        ListUtils.addAfter(input, 1, 22);
        assertThat(Arrays.asList(1, 5, 22, 3), is(input));
    }

    @Test
    public void removeIf() {
        final List<Integer> input
                = new ArrayList<>(Arrays.asList(2, 5, 3, 6, 9));
        ListUtils.removeIf(input, x -> x <= 5);
        assertThat(Arrays.asList(6, 9), is(input));
    }

    @Test
    public void replaceIf() {
        final List<Integer> input
                = new ArrayList<>(Arrays.asList(9, 6, 5, 8, 3, 6));
        ListUtils.replaceIf(input, x -> x % 2 != 0, 0);
        assertThat(Arrays.asList(0, 6, 0, 8, 0, 6), is(input));
    }

    @Test
    public void removeAll() {
        final List<Integer> source
                = new ArrayList<>(Arrays.asList(2, 8, 9, 9,5, 6, 3));
        final List<Integer> list
                = new ArrayList<>(Arrays.asList(8, 6, 9));
        ListUtils.removeAll(source, list);
        assertThat(source, is(Arrays.asList(2, 5, 3)));
    }
}
