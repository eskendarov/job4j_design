package ru.job4j.generics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleArrayTest {

    private SimpleArray<Integer> srcArray;

    @Test
    public void whenAddElement() {
        srcArray = new SimpleArray<>();
        srcArray.add(2);
        srcArray.add(4);
        srcArray.add(8);
        srcArray.add(12);
        SimpleArray<Integer> expected
                = new SimpleArray<>(new Integer[]{2, 4, 8, 12});
        assertThat(expected, is(srcArray));
    }

    @Test
    public void whenSetElement() {
        srcArray = new SimpleArray<>(new Integer[]{66, 33, 22, 45});
        srcArray.set(2, 999);
        assertThat(srcArray, is(new SimpleArray<>(
                new Integer[]{66, 33, 999, 45})));
    }

    @Test
    public void whenRemoveElement() {
        srcArray = new SimpleArray<>(new Integer[]{66, 33, 22, 45});
        srcArray.remove(2);
        assertThat(srcArray, is(new SimpleArray<>(new Integer[]{66, 33, 45})));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenIndexOutOfBounds() {
        srcArray = new SimpleArray<>(new Integer[]{66, 33, 22, 45});
        srcArray.get(-2);
    }

    @Test
    public void whenIterateElements() {
        srcArray = new SimpleArray<>(new Integer[]{66, 33, 22, 45});
        ArrayList<Integer> arrayList = new ArrayList<>();
        srcArray.iterator().forEachRemaining(arrayList::add);
        assertThat(arrayList, is(List.of(66, 33, 22, 45)));
    }
}
