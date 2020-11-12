package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {

    private int size = 0;
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        T first = null;
        boolean changeSize = false;
        for (int counter = 0; counter++ < size; ) {
            if (changeSize) {
                in.push(out.pop());
            } else {
                out.push(in.pop());
                if (counter == size) {
                    first = out.pop();
                    size--;
                    changeSize = true;
                    counter = 0;
                }
            }
        }
        if (first != null) {
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
