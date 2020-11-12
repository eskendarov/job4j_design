package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {

    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        if (!in.isEmpty()) {
            do {
                out.push(in.pop());
            } while (!in.isEmpty());
            final T first = out.pop();
            while (!out.isEmpty()) {
                in.push(out.pop());
            }
            return first;
        } else {
            throw new NoSuchElementException();
        }
    }

    public void push(T value) {
        in.push(value);
    }
}
