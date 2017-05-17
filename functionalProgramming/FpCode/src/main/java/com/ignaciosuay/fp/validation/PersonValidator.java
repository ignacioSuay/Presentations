package com.ignaciosuay.fp.validation;

import com.ignaciosuay.fp.model.Person;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import org.apache.commons.lang3.StringUtils;

public class PersonValidator {

    public Validation<Seq<String>, Person> validatePerson(String name, int age) {
        return Validation.combine(validateName(name), validateAge(age)).ap((name2, age2) -> new Person(name2, age2));
    }

    private Validation<String, String> validateName(String name) {
        return StringUtils.isAlpha(name)
                ? Validation.valid(name)
                : Validation.invalid("Name must contain only Unicode letters");
    }

    private Validation<String, Integer> validateAge(int age) {
        return age < 0 || age > 150
                ? Validation.invalid("Age must be between 0 and 150")
                : Validation.valid(age);
    }
}
