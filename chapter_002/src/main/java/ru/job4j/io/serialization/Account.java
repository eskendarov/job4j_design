package ru.job4j.io.serialization;

import java.util.Arrays;

public class Account {

    private final boolean connected;
    private final int id;
    private final String userName;
    private final Contact contact;
    private final String[] tokens;

    public Account(boolean connected, int id, String userName,
                   Contact contact, String[] tokens) {
        this.connected = connected;
        this.id = id;
        this.userName = userName;
        this.contact = contact;
        this.tokens = tokens;
    }

    @Override
    public String toString() {
        return "Account{"
                + "connected=" + connected
                + ", id=" + id
                + ", userName='" + userName
                + '\'' + ", contact=" + contact
                + ", tokens=" + Arrays.toString(tokens)
                + '}';
    }
}
