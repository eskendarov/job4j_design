package ru.job4j.io.serialization.json;

import org.json.JSONPropertyIgnore;

public class A {

    private B b;

    /**
     * без этой аннотации @JSONPropertyIgnore
     * возникает исключение StackOverflowError {@link B}
     */
    @JSONPropertyIgnore
    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public String getHello() {
        return "Hello";
    }
}
