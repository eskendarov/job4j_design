package ru.job4j.gc;

/**
 * @author Enver Eskendarov
 * @version 1.0 01.07.2021
 */

public class Person {

    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    /**
     * Метод finalize() вызывается перед тем как объект уничтожется, однако
     * этот метод является устаревшим и не рекомендуется его использовать.
     * Тем не менее мы можем применить для демонстрации работы сборки мусора.
     *
     * @see ru.job4j.gc.GCDemo
     */
    @Override
    protected void finalize() {
        System.out.printf("Removed %d %s%n", age, name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
