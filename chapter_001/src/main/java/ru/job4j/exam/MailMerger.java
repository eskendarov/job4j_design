package ru.job4j.exam;

import java.util.*;

public class MailMerger {

    public static Map<String, Set<String>> merge(Map<String, Set<String>> users) {
        final Map<String, String> tempMap = new HashMap<>();
        users.forEach((name, mails) -> {
            final StringBuilder user = new StringBuilder(name);
            mails.forEach(mail -> {
                if (tempMap.containsKey(mail)) {
                    user.replace(0, user.length(), tempMap.get(mail));
                }
            });
            mails.forEach(mail -> tempMap.put(mail, user.toString()));
        });
        final Map<String, Set<String>> result = new LinkedHashMap<>();
        tempMap.forEach((mail, user) -> {
            if (result.containsKey(user)) {
                result.get(user).add(mail);
            } else {
                result.put(user, new HashSet<>(Arrays.asList(mail)));
            }
        });
        return result;
    }
}
