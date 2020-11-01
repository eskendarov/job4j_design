package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class MemStoreTest {

    private final MemStore<User> store = new MemStore<>();

    @Test
    public void replace() {
        store.add(new User("We"));
        store.replace("We", new User("Ui"));
        User expected = new User("Ui");
        assertThat(store.findById("Ui"), is(expected));
    }

    @Test
    public void delete() {
        store.add(new User("We"));
        store.add(new User("Ew"));
        store.delete("We");
        assertNull(store.findById("We"));
    }
}
