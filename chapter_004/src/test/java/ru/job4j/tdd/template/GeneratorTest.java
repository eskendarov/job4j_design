package ru.job4j.tdd.template;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.tdd.MaxMin;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class GeneratorTest extends MaxMin {

    final Generator generator = new ExpressionGenerator();
    final String exp = "I am a Petr, Who are you? ";

    @Ignore
    @Test
    public void produce() {
        final String res = generator.produce(
                "I am a ${name}, Who are ${subject}? ",
                Map.of("name", "Petr", "subject", "you")
        );
        assertEquals(exp, res);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void keyNotFound() {
        generator.produce(
                "I am a ${name}, Who are ${subject}? ",
                Map.of("name", "Nina", "subject", "you")
        );
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void produceExtraMapValue() {
        generator.produce(
                "I am a ${name}, Who are ${subject}? ",
                Map.of("name", "Petr", "subject", "you", "Poker", "Face")
        );
    }
}
