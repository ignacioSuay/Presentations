package com.ignaciosuay.fp;


import io.vavr.control.Option;
import io.vavr.control.Try;
import org.junit.Test;

import static io.vavr.API.*;
import static io.vavr.Patterns.*;
import static org.junit.Assert.assertEquals;

public class PatternMatchingTests {

    @Test
    public void testDataPatternMatching() {

        int input = 2;
        String output = Match(input).of(
                Case($(1), "one"),
                Case($(2), "two"),
                Case($(3), "three"),
                Case($(), "?"));

        assertEquals("two", output);
    }

    @Test
    public void testDataPM() {
        Try<Integer> divideBy0 = Try.of(() -> 1 / 0);

        Integer a = Match(divideBy0).of(
                Case($Success($()), x -> 1),
                Case($Failure($()), x -> 0)
        );
        System.out.println(a);

        Option option = Option.of(1);

        System.out.println(Match(option).of(
                Case($Some($(1)), () -> "defined1"),
                Case($Some($()), () -> "defined"),
                Case($None(), () -> "empty")
        ));


    }
}
