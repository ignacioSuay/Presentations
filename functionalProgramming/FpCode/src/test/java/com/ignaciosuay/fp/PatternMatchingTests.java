package com.ignaciosuay.fp;


import com.ignaciosuay.fp.model.Person;
import com.ignaciosuay.fp.validation.PersonValidator;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Option;
import io.vavr.control.Try;
import io.vavr.control.Validation;
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
    public void testTryPM() {
        Try<Integer> divideBy0 = Try.of(() -> 1 / 0);
        Integer a = Match(divideBy0).of(
                Case($Success($()), x -> 1),
                Case($Failure($()), x -> 0)
        );
        assert a == 0;
    }

    @Test
    public void testOptionPM() {
        Option option = Option.of(1);
        String optionResult = Match(option).of(
                Case($Some($(1)), () -> "is 1"),
                Case($Some($()), () -> "has a value but it is not 1"),
                Case($None(), () -> "empty")
        );
        assert optionResult.equals("is 1");
    }

    @Test
    public void testDatePM() {

        PersonValidator personValidator = new PersonValidator();
        Validation<Seq<String>, Person> suay = personValidator.validatePerson("suay", -1);
        String validationResult = Match(suay).of(
                Case($Valid($()), "valid"),
                Case($Invalid($(List.of("Age must be between 0 and 150"))), "invalid age"),
                Case($Invalid($()), "any other error")
        );
        assert validationResult.equals("invalid age");
    }
}
