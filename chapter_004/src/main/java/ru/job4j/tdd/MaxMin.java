package ru.job4j.tdd;

import java.util.Comparator;
import java.util.List;

/**
 * Date: 20.11.2021
 * Project: JOB4J_DESIGN
 * Class: MaxMin - класс для поиска максимального и минимального элемента по критерию java.util.Comparator.
 * Реализация без Stream API и методов Collections.
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
public class MaxMin {

    public <T> T max(List<T> list, Comparator<T> comparator) {
        return findVal(list, comparator);
    }

    public <T> T min(List<T> list, Comparator<T> comparator) {
        return findVal(list, comparator.reversed());
    }

    private <T> T findVal(List<T> list, Comparator<T> comparator) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("list is empty!");
        }
        T res = list.get(0);
        for (T next : list) {
            res = comparator.compare(res, next) >= 0 ? res : next;
        }
        return res;
    }
}
