package ru.job4j.collection;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {

    private final SimpleArray<E> container = new SimpleArray<>();

    public boolean add(E value) {
        for (E el : container) {
            if (el.equals(value)) {
                return false;
            }
        }
        container.add(value);
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return container.iterator();
    }
}
