package ru.job4j.tdd;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.tdd.src.Account;
import ru.job4j.tdd.src.AccountCinema;
import ru.job4j.tdd.src.Cinema;
import ru.job4j.tdd.src.Cinema3D;
import ru.job4j.tdd.src.Session;
import ru.job4j.tdd.src.Session3D;
import ru.job4j.tdd.src.Ticket;

import java.util.Calendar;
import java.util.Collections;

import static java.util.Calendar.NOVEMBER;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class CinemaTest {

    private final Calendar date = Calendar.getInstance();
    private final Account account = new AccountCinema();
    private final Cinema cinema = new Cinema3D();
    private final Session session = new Session3D();
    private Ticket ticket = null;

    @Test
    public void byuSuccess() {
        date.set(2020, NOVEMBER, 10, 23, 0);
        cinema.add(session);
        ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(cinema.buy(account, 1, 1, date)));
    }


    @Test
    public void ticketsNotFound() {
        cinema.add(session);
        date.set(2021, NOVEMBER, 21, 12, 45);
        ticket = cinema.buy(account, 0, 0, date);
        assertNull(ticket);
    }

    @Ignore
    @Test
    public void addSessionAndFound() {
        cinema.add(session);
        assertEquals(Collections.singletonList(session), cinema.find(session1 -> true));
    }

    @Ignore
    @Test
    public void sessionNotFound() {
        assertEquals(Collections.emptyList(), cinema.find(session3D -> false));
    }
}
