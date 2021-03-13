package ru.job4j.spammer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * ImportDB - Переводит данные из файла в базу данных PostgreSQL
 *
 * @author Enver Eskendarov
 * @version 1.0 13/03/2021
 */
public class ImportDB {

    private final ResourceBundle config;
    private final String dump;

    public ImportDB(ResourceBundle config, String dump) {
        this.config = config;
        this.dump = dump;
    }

    public List<User> load() {
        final List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().map(s -> s.split(";", 3)) // line pattern: "name;email;"
                    .forEach(data -> users.add(new User(data[0], data[1])));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void save(List<User> users) {
        try (Connection cnt = DriverManager.getConnection(
                config.getString("postgresql.connection.url"),
                config.getString("postgresql.username"),
                config.getString("postgresql.password"))
        ) {
            Class.forName(config.getString("postgresql.driver.class"));
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement(
                        "insert into users(name,email) values (?,?)")
                ) {
                    ps.setString(1, user.getName());
                    ps.setString(2, user.getEmail());
                    ps.execute();
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static class User {

        private final String name;
        private final String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }
    }

    public static void main(String[] args) {
        final ImportDB db = new ImportDB(
                ResourceBundle.getBundle("spammer"), "./dump.txt"
        );
        db.save(db.load());
    }
}