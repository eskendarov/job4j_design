package ru.job4j.collection;

import org.junit.Test;
import ru.job4j.collection.list.SimpleLinkedArray;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleLinkedArrayTest {

    @Test
    public void whenAddThenGet() {
        SimpleLinkedArray<Integer> array = new SimpleLinkedArray<>();
        array.add(33);
        Integer rsl = array.get(0);
        assertThat(rsl, is(33));
    }

    @Test
    public void whenAddThenIt() {
        SimpleLinkedArray<Integer> array = new SimpleLinkedArray<>();
        array.add(33);
        Integer rsl = array.iterator().next();
        assertThat(rsl, is(33));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        SimpleLinkedArray<Integer> array = new SimpleLinkedArray<>();
        array.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        SimpleLinkedArray<Integer> array = new SimpleLinkedArray<>();
        array.add(33);
        array.get(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleLinkedArray<Integer> array = new SimpleLinkedArray<>();
        array.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddElementThenCorruptedIt() {
        SimpleLinkedArray<Integer> array = new SimpleLinkedArray<>();
        array.add(33);
        Iterator<Integer> it = array.iterator();
        array.add(44);
        it.next();
    }
}
