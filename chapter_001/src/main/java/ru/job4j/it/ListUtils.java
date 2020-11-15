package ru.job4j.it;

import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.function.Predicate;

public class ListUtils {

    public static <T> void addBefore(List<T> input, int index, T value) {
        Objects.checkIndex(index, input.size());
        final ListIterator<T> i = input.listIterator();
        while (i.hasNext()) {
            if (i.nextIndex() == index) {
                i.add(value);
                return;
            }
            i.next();
        }
    }

    public static <T> void addAfter(List<T> input, int index, T value) {
        Objects.checkIndex(index, input.size());
        final ListIterator<T> i = input.listIterator();
        while (i.hasNext()) {
            if (i.previousIndex() == index) {
                i.add(value);
                return;
            }
            i.next();
        }
    }

    public static <T> List<T> removeIf(List<T> input, Predicate<T> filter) {
        final ListIterator<T> i = input.listIterator();
        while (i.hasNext()) {
            if (filter.test(i.next())) {
                i.remove();
            }
        }
        return input;
    }

    public static <T> List<T> replaceIf(List<T> input, Predicate<T> filter, T value) {
        final ListIterator<T> i = input.listIterator();
        while (i.hasNext()) {
            if (filter.test(i.next())) {
                i.set(value);
            }
        }
        return input;
    }

    public static <T> List<T> removeAll(List<T> srcList, List<T> removeElems) {
        final ListIterator<T> srcIt = srcList.listIterator();
        while (srcIt.hasNext()) {
            final T srcElem = srcIt.next();
            for (T el : removeElems) {
                if (Objects.equals(srcElem, el)) {
                    srcIt.remove();
                }
            }
        }
        return srcList;
    }
}
