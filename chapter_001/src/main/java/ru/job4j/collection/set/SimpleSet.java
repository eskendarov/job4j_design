package ru.job4j.collection.set;

import ru.job4j.collection.list.SimpleArray;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<E> implements Iterable<E> {

    private final SimpleArray<E> container = new SimpleArray<>();

    public boolean contains(E value) {
        for (E el : container) {
            if (Objects.equals(el, value)) {
                return true;
            }
        }
        return false;
    }

    public void add(E value) {
        if (!contains(value)) {
            container.add(value);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return container.iterator();
    }
}
