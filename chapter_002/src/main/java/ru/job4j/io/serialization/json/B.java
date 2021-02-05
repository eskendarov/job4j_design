package ru.job4j.io.serialization.json;

import org.json.JSONObject;

public class B {

    private A a;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    /**
     * При преобразовании объектов в json-строку возможно рекурсивное
     * зацикливание, простейший пример, когда объект {@link A} содержит ссылку
     * на объект {@link B}, а он в свою очередь ссылается на первоначальный
     * объект {@link A}. При выполнении будут осуществляться переходы по ссылке
     * и сериализация до возникновения исключения StackOverflowError.
     * Чтобы избежать исключения необходимо разорвать цепочку, как правило это
     * делается в момент перехода по ссылке на объект, который уже сериализован.
     * В библиотеке JSON-Java (org.json) для этого существует
     * аннотация @JSONPropertyIgnore:
     */
    public static void main(String[] args) {
        final A a = new A();
        final B b = new B();
        a.setB(b);
        b.setA(a);
        System.out.println(new JSONObject(b));
    }
}
