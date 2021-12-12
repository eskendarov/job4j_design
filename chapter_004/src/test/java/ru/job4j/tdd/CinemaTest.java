package ru.job4j.tdd;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.tdd.src.*;

import java.util.Calendar;
import java.util.Collections;

import static java.util.Calendar.HOUR;
import static java.util.Calendar.NOVEMBER;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CinemaTest {

    private final Calendar date = Calendar.getInstance();
    private final Account account = new AccountCinema();
    private final Cinema cinema = new Cinema3D();
    private final Session session = new Session3D();
    private Ticket ticket = null;

    @Test
    public void whenByeTicketThenSuccess() {
        date.set(2020, NOVEMBER, 10, 23, 0);
        cinema.add(session);
        ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(cinema.buy(account, 1, 1, date)));
    }

    @Test
    public void whenTicketNotFoundThenAssertNull() {
        cinema.add(session);
        date.set(2021, NOVEMBER, 21, 12, 45);
        ticket = cinema.buy(account, 0, 0, date);
        assertNull(ticket);
    }

    @Ignore
    @Test
    public void whenAddSessionThenFoundIt() {
        cinema.add(session);
        assertEquals(Collections.singletonList(session), cinema.find(session1 -> true));
    }

    @Ignore
    @Test
    public void whenSessionNotFoundThenResultIsEmpty() {
        assertEquals(Collections.emptyList(), cinema.find(session3D -> false));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenPlaceIsBusyThenThrowException() {
        date.set(2021, NOVEMBER, 29, 12, 0);
        cinema.buy(account, 4, 3, date);
        cinema.buy(account, 4, 3, date);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenPlaceIsNotValidThenThrowException() {
        date.set(2021, NOVEMBER, 29, 12, 0);
        cinema.buy(account, -1, -1, date);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenTimeIsPassedThenThrowException() {
        date.add(HOUR, -1);
        cinema.buy(account, 1, 1, date);
    }
}
