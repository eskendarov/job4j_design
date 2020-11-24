package ru.job4j.collection.list;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {

    private int modCount = 0;
    private int size = 0;
    @SuppressWarnings("unchecked")
    private T[] container = (T[]) new Object[0];

    private void grow() {
        final int newCapacity = (container.length * 3) / 2 + 1;
        container = Arrays.copyOf(container, newCapacity);
    }

    public void add(T value) {
        if (size == container.length) {
            grow();
        }
        container[size++] = value;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    public void set(int index, T value) {
        Objects.checkIndex(index, size);
        container[index] = value;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;
            int position = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return position < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[position++];
            }
        };
    }
}
