package ru.job4j.gc;

/**
 * @author Enver Eskendarov
 * @version 1.0 23.08.2021
 */
public class User {

    private final String login;
    private final int num;

    public User(String login, int num) {
        this.login = login;
        this.num = num;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.printf("Removed %s %d%n", login, num);
    }
}
