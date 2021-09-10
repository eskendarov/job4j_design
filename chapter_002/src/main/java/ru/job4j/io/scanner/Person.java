package ru.job4j.io.scanner;

/**
 * @author Enver Eskendarov
 * @version 1.0 18.07.2021
 */
public class Person {

    private final String
            name,
            age,
            birthDate,
            education,
            children;

    public Person(String name,
                  String age,
                  String birthDate,
                  String education,
                  String children) {
        this.name = name;
        this.age = age;
        this.birthDate = birthDate;
        this.education = education;
        this.children = children;
    }

    public String get(String arg) {
        switch (arg) {
            case "name":
                return name;
            case "age":
                return age;
            case "birthDate":
                return birthDate;
            case "education":
                return education;
            case "children":
                return children;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Person{name='%s', age='%s', birthDate='%s', education='%s', children='%s'}",
                name, age, birthDate, education, children
        );
    }
}
