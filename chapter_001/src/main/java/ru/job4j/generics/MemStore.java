package ru.job4j.generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        return Collections.replaceAll(mem, findById(id), model);
    }

    @Override
    public boolean delete(String id) {
        return mem.remove(findById(id));
    }

    @Override
    public T findById(String id) {
        for (T el : mem) {
            if (id.equals(el.getId())) {
                return el;
            }
        }
        return null;
    }
}
