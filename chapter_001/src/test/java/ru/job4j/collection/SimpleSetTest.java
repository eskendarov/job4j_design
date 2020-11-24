package ru.job4j.collection;

import org.junit.Test;
import ru.job4j.collection.set.SimpleSet;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class SimpleSetTest {

    @Test
    public void contains() {
        final SimpleSet<String> set = new SimpleSet<>();
        set.add("Java");
        set.add("Oracle");
        assertFalse(set.contains("AliExpress"));
    }

    @Test
    public void iterator() {
        final SimpleSet<Integer> set = new SimpleSet<>();
        set.add(7);
        set.add(33);
        set.add(78);
        final Iterator<Integer> setIt = set.iterator();
        setIt.next();
        assertThat(setIt.next(), is(33));
    }
}
