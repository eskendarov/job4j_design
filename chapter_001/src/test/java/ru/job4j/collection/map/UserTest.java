package ru.job4j.collection.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserTest {

    @Test
    public void whenOverrideEqualsAndHashCode() {
        final User user1 = new User("Neo", new GregorianCalendar(1989, Calendar.APRIL, 22), 2);
        final User user2 = new User("Tom", new GregorianCalendar(2000, Calendar.MAY, 22), 1);
        final User user3 = null;
        final Map<User, Object> mapUser = new HashMap<>();
        mapUser.put(user1, "User_1");
        mapUser.put(user2, "User_2");
        mapUser.put(user3, "User_3");
        mapUser.put(null, "User_4");
        assertThat(mapUser.get(user1), is("User_1"));
        assertThat(mapUser.get(user3), is("User_4"));
    }
}
