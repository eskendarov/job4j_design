package ru.job4j.collection;

public class SimpleStack<T> {

    private final ForwardLinked<T> container = new ForwardLinked<>();

    public T pop() {
        return container.deleteLast();
    }

    public void push(T value) {
        container.add(value);
    }
}
