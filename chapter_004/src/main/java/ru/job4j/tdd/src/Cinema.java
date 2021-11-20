package ru.job4j.tdd.src;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

/**
 * Project: job4j_design (20.11.2021)
 * <p>
 * Interface: Cinema
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
public interface Cinema {

    List<Session> find(Predicate<Session> filter);

    Ticket buy(Account account, int row, int column, Calendar date);

    void add(Session session);
}
