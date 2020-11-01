package ru.job4j.generics;

public class User extends Base {

    protected User(String id) {
        super(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return null != obj
                && obj.getClass() == User.class
                && getId().equals(((User) obj).getId());
    }
}
