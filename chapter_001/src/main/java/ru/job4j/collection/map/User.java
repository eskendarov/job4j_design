package ru.job4j.collection.map;

import java.util.Calendar;
import java.util.Objects;

import static java.util.Calendar.*;

public class User {

    private final String name;
    private final Calendar birthday;
    private int children;

    public User(String name, Calendar birthday, int children) {
        this.name = name;
        this.birthday = birthday;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        final User other = (User) obj;
        return this.name.equals(other.name)
                && this.birthday.equals(other.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday);
    }

    @Override
    public String toString() {
        return String.format("{name: %s, birthday: %s.%s.%s, children: %s}",
                name,
                birthday.get(DAY_OF_MONTH),
                birthday.get(MONTH),
                birthday.get(YEAR),
                children
        );
    }
}
