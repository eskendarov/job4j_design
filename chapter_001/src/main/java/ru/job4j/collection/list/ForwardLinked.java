package ru.job4j.collection.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {

    private Node<T> first;
    private Node<T> last;

    public void add(T value) {
        final Node<T> oldLast = last;
        final Node<T> newNode = new Node<>(oldLast, value, null);
        last = newNode;
        if (first == null) {
            first = newNode;
        } else if (oldLast != null) {
            oldLast.next = last;
        }
    }

    public T deleteFirst() {
        if (first != null) {
            final T value = first.value;
            final Node<T> newFirst = first.next;
            first.next = null;
            first = newFirst;
            return value;
        } else {
            throw new NoSuchElementException();
        }
    }

    public T deleteLast() {
        if (last != null) {
            final T value = last.value;
            final Node<T> newLast = last.prev;
            last.prev = null;
            last = newLast;
            return value;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> node = first;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                final T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {

        private final T value;
        private Node<T> next;
        private Node<T> prev;

        Node(Node<T> prev, T value, Node<T> next) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }
}
