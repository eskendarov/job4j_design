package ru.job4j.collection;

public class SimpleStack<T> {

    private final ForwardLinked<T> container = new ForwardLinked<>();
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public T pop() {
        if (size != 0) {
            size--;
        }
        return container.deleteLast();
    }

    public void push(T value) {
        container.add(value);
        size++;
    }
}
