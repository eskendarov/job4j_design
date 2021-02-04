package ru.job4j.io.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UsageJSON {

    public static void main(String[] args) {
        final Account account = new Account(true, 23, "Kot",
                new Contact(55, "734552"),
                new String[]{"0Ne^x5", "9]Egz;", ")U9|Io", "E0$}xK", "}2lUzL"});
        /* Преобразовние объекта account в json-строку. */
        final Gson gson = new GsonBuilder().create();
        final String accountJSON = gson.toJson(account);
        System.out.println(accountJSON);
        /* Извлечение объекта account из json-строки. */
        final Account accFromJSON = gson.fromJson(accountJSON, Account.class);
        System.out.println(accFromJSON);
    }
}
