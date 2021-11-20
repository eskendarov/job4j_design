package ru.job4j.tdd.src;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

/**
 * Project: job4j_design (20.11.2021)
 * <p>
 * Class: Cinema3D
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
public class Cinema3D implements Cinema {

    @Override
    public List<Session> find(Predicate<Session> filter) {
        return null;
    }

    @Override
    public Ticket buy(Account account, int row, int column, Calendar date) {
        return null;
    }

    @Override
    public void add(Session session) {

    }
}
