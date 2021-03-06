package ru.job4j.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * TableEditor class handles SQL queries for tables
 *
 * @author Enver Eskendarov
 * @version 1.0 10/03/2021
 */
public class TableEditor implements AutoCloseable {

    private static Connection connection;

    /**
     * @param baseName - Имя файла настроек PostgreSQL
     */
    public TableEditor(String baseName) {
        /* ResourceBundle принимает имя файла без расширения '.properties' */
        final ResourceBundle res = ResourceBundle.getBundle(baseName);
        try {
            Class.forName(res.getString("postgresql.driver.class"));
            connection = DriverManager.getConnection(
                    res.getString("postgresql.connection.url"),
                    res.getString("postgresql.username"),
                    res.getString("postgresql.password")
            );
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        execute(String.format("create table if not exists %s(%s, %s);",
                tableName, "id serial primary key", "name varchar(255)"));
    }

    public void dropTable(String tableName) {
        execute(String.format("drop table %s;", tableName));
    }

    public void addColumn(String tableName, String columnName, String type) {
        execute(String.format("alter table %s add %s %s;",
                tableName, columnName, type));
    }

    public void dropColumn(String tableName, String columnName) {
        execute(String.format("alter table %s drop column %s;",
                tableName, columnName));
    }

    public void renameColumn(String tableName, String columnName,
                             String newColumnName) {
        execute(String.format("alter table %s rename column %s to %s;",
                tableName, columnName, newColumnName));
    }

    /**
     * @return возвращает информацию о таблице в виде строки
     */
    public static String getScheme(String tableName) {
        final StringBuilder scheme = new StringBuilder();
        try (ResultSet columns = connection.getMetaData()
                .getColumns(null, null, tableName, null)
        ) {
            scheme.append(String.format("%-15s %-15s%n", "column", "type"));
            while (columns.next()) {
                scheme.append(String.format("%-15s %-15s%n",
                        columns.getString("COLUMN_NAME"),
                        columns.getString("TYPE_NAME")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scheme.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    private void execute(String sqlQuery) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sqlQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final TableEditor sqlQuery = new TableEditor("application");
        final String table = "demo_table";
        sqlQuery.createTable(table);
        sqlQuery.dropTable(table);
        sqlQuery.createTable(table);
        sqlQuery.addColumn(table, "age", "int");
        sqlQuery.addColumn(table, "laptop", "varchar");
        sqlQuery.dropColumn(table, "age");
        sqlQuery.renameColumn(table, "laptop", "mobile_phone");
        System.out.println(getScheme("demo_table"));
    }
}
