package ru.job4j.tdd.template;

import java.util.Map;

/**
 * Project: job4j_design (20.11.2021)
 * <p>
 * Interface: Generator
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
public interface Generator {

    String produce(String template, Map<String, String> args);
}
