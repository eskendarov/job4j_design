package ru.job4j.collection.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SimpleHashMapTest {

    private final SimpleHashMap<User, String> map = new SimpleHashMap<>();
    private final User user1 = new User("Neo", new GregorianCalendar(1989,
            Calendar.APRIL, 22), 2);
    private final User user2 = new User("Robert", new GregorianCalendar(1989,
            Calendar.APRIL, 22), 2);

    @Test
    public void insert() {
        assertTrue(map.insert(user1, "User_1"));
    }

    @Test
    public void get() {
        map.insert(user1, "User_1");
        map.insert(user2, "User_2");
        assertThat(map.get(user1), is("User_1"));
    }

    @Test(expected = NoSuchElementException.class)
    public void delete() {
        map.insert(user1, "User_1");
        map.insert(user2, "User_2");
        map.delete(user1);
        assertThat(map.get(user1), is("User_1"));

    }

    @Test
    public void iterator() {
        map.insert(user2, "Test");
        map.insert(null, "Test");
        assertThat(map.iterator().next().getValue(), is("Test"));
    }
}
