package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> mem = new HashMap<>();

    @Override
    public void add(T model) {
        mem.putIfAbsent(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        return delete(id) && mem.put(model.getId(), model) == model;
    }

    @Override
    public boolean delete(String id) {
        return null != mem.remove(id);
    }

    @Override
    public T findById(String id) {
        return mem.get(id);
    }
}
