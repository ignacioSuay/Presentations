package com.ignaciosuay.fp;

import com.ignaciosuay.fp.model.Person;
import com.ignaciosuay.fp.validation.PersonValidator;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import org.junit.Test;

public class ValidationTest {

    @Test
    public void testValidation(){
        PersonValidator personValidator = new PersonValidator();

        Validation<Seq<String>, Person> suay = personValidator.validatePerson("suay", 31);
        assert suay.isValid();

        Validation<Seq<String>, Person> suayInvalid = personValidator.validatePerson("suay-invalid", 31);
        assert !suayInvalid.isValid();
        assert suayInvalid.getError().apply(0).equals("Name must contain only Unicode letters");

        Validation<Seq<String>, Person> suayInvalid2 = personValidator.validatePerson("suay-invalid", 160);
        assert !suayInvalid.isValid();
        assert suayInvalid2.getError().apply(0).equals("Name must contain only Unicode letters");
        assert suayInvalid2.getError().apply(1).equals("Age must be between 0 and 150");
    }
}