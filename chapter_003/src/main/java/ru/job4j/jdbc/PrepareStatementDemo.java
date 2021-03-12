package ru.job4j.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * PrepareStatementDemo class executes DML operations:
 * - INSERT, SELECT, UPDATE, DELETE.
 *
 * @author Enver Eskendarov
 * @version 1.0 11/03/2021
 */
public class PrepareStatementDemo implements AutoCloseable {

    private Connection connection;

    public PrepareStatementDemo() {
        initConnection();
    }

    public void initConnection() {
        final ResourceBundle res = ResourceBundle.getBundle("application");
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

    public City insert(City city) {
        try (PreparedStatement statement = connection.prepareStatement(
                "insert into cities(name, population) values (?, ?)",
                Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    city.setId(generatedKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return city;
    }

    public City update(City city) {
        try (PreparedStatement statement = connection.prepareStatement(
                "update cities set name = ?, population = ? where id = ?")
        ) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.setInt(3, city.getId());
            if (statement.executeUpdate() < 0) {
                city = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return city;
    }

    public boolean delete(int id) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(
                "delete from cities where id = ?")
        ) {
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<City> findAll() {
        final List<City> cities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "select * from cities")
        ) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cities.add(new City(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("population")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cities;
    }

    public City getCityById(int id) {
        City city = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "select * from cities where id = ?")
        ) {
            statement.setInt(1, id);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                city = new City(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("population")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return city;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    public static void main(String[] args) {
        final PrepareStatementDemo query = new PrepareStatementDemo();
        query.initConnection();
        final int tambovId = query.insert(new City("Tambov", 34000)).getId();
        query.insert(new City("SPb", 800000));
        final int penzaId = query.insert(new City("Penza", 432121)).getId();
        query.update(new City(penzaId, "Moscow", 7433221));
        System.out.println("getCityById: " + query.getCityById(penzaId));
        System.out.printf("City where id=%d deleted: "
                + query.delete(tambovId) + System.lineSeparator(), tambovId);
        query.findAll().forEach(System.out::println);
    }
}
