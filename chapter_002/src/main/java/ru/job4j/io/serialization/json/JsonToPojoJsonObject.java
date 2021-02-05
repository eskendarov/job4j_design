package ru.job4j.io.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.io.serialization.Account;
import ru.job4j.io.serialization.Contact;

import java.util.ArrayList;
import java.util.List;

public class JsonToPojoJsonObject {

    public static void main(String[] args) {
        /* JSONObject из json-строки строки */
        final JSONObject jsonContact = new JSONObject(
                "{\"zipCode\":34,\"phone\":\"+7(924)111-111-11-11\"}");
        /* JSONArray из ArrayList */
        final List<String> tokens = new ArrayList<>();
        tokens.add("2e2s");
        tokens.add("32e2");
        tokens.add("iuh6");
        final JSONArray jsonTokens = new JSONArray(tokens);
        /* JSONObject напрямую методом put */
        final Account account = new Account(false, 30, "Worker",
                new Contact(34, "232442"),
                new String[]{"2e2", "32e2", "23e2"});
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("connected", account.isConnected());
        jsonObject.put("id", account.getId());
        jsonObject.put("user", account.getUser());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("tokens", jsonTokens);
        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());
        /* Преобразуем объект account в json-строку */
        System.out.println(new JSONObject(account).toString());
    }
}
