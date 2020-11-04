package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleStack<T> {

    private Node<T> last;

    public T pop() {
        if (null != last) {
            final T lastValue = last.value;
            final Node<T> prev = last.prev;
            last = null;
            last = prev;
            return lastValue;
        } else {
            throw new NoSuchElementException();
        }
    }

    public void push(T value) {
        final Node<T> newNode = new Node<>(value, null);
        if (last == null) {
            last = newNode;
            return;
        }
        final Node<T> prev = last;
        last = newNode;
        last.prev = prev;
    }

    private static class Node<T> {

        T value;
        Node<T> prev;

        public Node(T value, Node<T> prev) {
            this.value = value;
            this.prev = prev;
        }
    }
}
