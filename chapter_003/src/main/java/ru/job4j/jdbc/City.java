package ru.job4j.jdbc;

/**
 * City - POJO class
 *
 * @author Enver Eskendarov
 * @version 1.0 11/03/2021
 */
public class City {

    private int id;

    private String name;

    private int population;

    public City(int id, String name, int population) {
        this.id = id;
        this.name = name;
        this.population = population;
    }

    public City(String name, int population) {
        this.name = name;
        this.population = population;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return String.format(
                "City{id=%d, name=%s, population=%d}",
                id, name, population
        );
    }
}
