package ru.job4j.jdbc;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Database connection class
 *
 * @author Enver Eskendarov
 * @version 1.0 10/03/2021
 */
public class ConnectionDemo {

    public static void main(String[] args)
            throws ClassNotFoundException, SQLException, IOException {
        final Properties p = new Properties();
        p.load(new FileReader("application.properties"));
        /* Register jdbc driver class. */
        Class.forName(p.getProperty("postgresql.driver.class"));
        try (Connection connection = DriverManager.getConnection(
                p.getProperty("postgresql.connection.url"),
                p.getProperty("postgresql.username"),
                p.getProperty("postgresql.password"))
        ) {
            final DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getURL());
            System.out.println(metaData.getUserName());
        }
    }
}
