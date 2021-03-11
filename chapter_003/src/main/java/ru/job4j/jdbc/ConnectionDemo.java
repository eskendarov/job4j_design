package ru.job4j.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Database connection class
 *
 * @author Enver Eskendarov
 * @version 1.0 10/03/2021
 */
public class ConnectionDemo {

    public static void main(String[] args)
            throws ClassNotFoundException, SQLException {
        /* ResourceBundle accepts filename without extension '.properties' */
        final ResourceBundle res = ResourceBundle.getBundle("application");
        /* Register jdbc driver class. */
        try (Connection connection = DriverManager.getConnection(
                res.getString("postgresql.connection.url"),
                res.getString("postgresql.username"),
                res.getString("postgresql.password"))
        ) {
            Class.forName(res.getString("postgresql.driver.class"));
            final DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getURL());
            System.out.println(metaData.getUserName());
        }
    }
}
