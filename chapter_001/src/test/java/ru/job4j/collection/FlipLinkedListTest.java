package ru.job4j.collection;

import org.junit.Test;
import ru.job4j.collection.list.FlipLinkedList;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FlipLinkedListTest {

    @Test
    public void whenAddThenIt() {
        final FlipLinkedList<Integer> linked = new FlipLinkedList<>();
        linked.add(1);
        linked.add(2);
        final Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenAddAndRevertThenIt() {
        final FlipLinkedList<Integer> linked = new FlipLinkedList<>();
        linked.add(1);
        linked.add(2);
        linked.revert();
        final Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
    }
}
