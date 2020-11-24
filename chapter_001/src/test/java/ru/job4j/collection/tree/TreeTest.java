package ru.job4j.collection.tree;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TreeTest {

    @Test
    public void whenAddExistEl() {
        final Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertFalse(tree.add(1, 2));
    }

    @Test
    public void when6ElFindLastThen6() {
        final Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.findBy(6).isPresent(), is(true));
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        final Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(tree.findBy(7).isPresent(), is(false));
    }

    @Test
    public void whenTreeIsBinary() {
        final Tree<Integer> tree = new Tree<>(3);
        tree.add(3, 6);
        tree.add(6, 2);
        tree.add(6, 3);
        tree.add(2, 5);
        tree.add(2, 4);
        assertTrue(tree.isBinary());
    }

    @Test
    public void whenTreeIsNotBinary() {
        final Tree<Integer> tree = new Tree<>(5);
        tree.add(5, 2);
        tree.add(5, 1);
        tree.add(2, 4);
        tree.add(2, 7);
        tree.add(2, 6);
        assertFalse(tree.isBinary());
    }
}
