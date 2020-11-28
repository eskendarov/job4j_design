package ru.job4j.exam;

import java.util.List;
import java.util.Objects;

public class Analyze {

    public Info diff(List<User> previous, List<User> current) {
        int changed = 0;
        int common = 0;
        for (User prev : previous) {
            for (User curr : current) {
                if (prev.equals(curr)) {
                    common++;
                } else {
                    if (prev.id == curr.id) {
                        changed++;
                    }

                }
            }
        }
        int deleted = previous.size() - common;
        final int added = current.size() - common - changed;
        return new Info(added, changed, deleted);
    }

    public static class User {

        private final int id;
        private final String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof User)) {
                return false;
            }
            final User user = (User) o;
            return id == user.id
                    && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        @Override
        public String toString() {
            return String.format("User{id = %d, name = %s}", id, name);
        }
    }

    public static class Info {

        private final int add;
        private final int change;
        private final int delete;

        public Info(int add, int change, int delete) {
            this.add = add;
            this.change = change;
            this.delete = delete;
        }

        @Override
        public String toString() {
            return String.format("Info{added = %d, changed = %d, deleted = %d}",
                    add, change, delete);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Info)) {
                return false;
            }
            Info info = (Info) o;
            return add == info.add
                    && change == info.change
                    && delete == info.delete;
        }

        @Override
        public int hashCode() {
            return Objects.hash(add, change, delete);
        }
    }
}
