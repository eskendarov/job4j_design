package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {

    private int size = 0;
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        for (int i = 0; i < size; i++) {
            out.push(in.pop());
        }
        T first;
        if ((first = out.pop()) != null) {
            size--;
            for (int i = 0; i < size; i++) {
                in.push(out.pop());
            }
            return first;
        } else {
            throw new NoSuchElementException();
        }
    }

    public void push(T value) {
        in.push(value);
        size++;
    }
}
