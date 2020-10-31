package ru.job4j.generics;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    private int pos = 0;
    private int capacity = 10;
    private T[] arr;

    public SimpleArray(T[] arr) {
        this.arr = arr;
        pos = arr.length;
    }

    public SimpleArray() {
        arr = (T[]) new Object[capacity];
    }

    public void add(T element) {
        if (pos == arr.length) {
            capacity = (capacity * 3) / 2 + 1;
            arr = Arrays.copyOf(arr, capacity);
        }
        arr[pos++] = element;
    }

    public int size() {
        return pos;
    }

    public boolean set(int index, T element) {
        try {
            arr[Objects.checkIndex(index, size())] = element;
            return true;
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean remove(int index) {
        var temp = (T[]) new Object[capacity];
        try {
            int indexTemp = Objects.checkIndex(index, size());
            System.arraycopy(arr, 0, temp, 0, indexTemp);
            System.arraycopy(arr, indexTemp + 1, temp, indexTemp, arr.length - 1 - indexTemp);
            arr = temp;
            pos--;
            return true;
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return false;
        }
    }

    public T get(int index) {
        return arr[Objects.checkIndex(index, size())];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int point = 0;

            @Override
            public boolean hasNext() {
                return point < arr.length && null != arr[point];
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return arr[point++];
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        var other = (SimpleArray<T>) o;
        if (this.size() != other.size()) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (!arr[i].equals(other.arr[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(capacity);
        result = 31 * result + Arrays.hashCode(arr);
        return result;
    }

    @Override
    public String toString() {
        if (!this.iterator().hasNext())
            return "[]";
        var result = new StringBuilder();
        result.append('[');
        for (; ; ) {
            result.append(this.iterator().next());
            if (!this.iterator().hasNext())
                return result.append(']').toString();
            result.append(',').append(' ');
        }
    }
}
