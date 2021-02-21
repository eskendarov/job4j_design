package ru.job4j.exam;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MailMergerTest {

    @Test
    public void test() throws FileNotFoundException {
        final Map<String, Set<String>> users = new Gson().fromJson(
                new FileReader("users.json"),
                new TypeToken<LinkedHashMap<String ,Set<String>>>() {}.getType()
        );
        final var result = MailMerger.merge(users);
        final var expected = Map.of(
                "User1", Set.of("xxx@ya.ru", "foo@gmail.com",
                        "lol@mail.ru", "ups@pisem.net", "aaa@bbb.ru"),
                "User3", Set.of("xyz@pisem.net", "vasya@pupkin.com")
        );
        assertThat(result, is(expected));
    }
}
