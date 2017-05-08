package com.ignaciosuay.fp;

import com.ignaciosuay.fp.model.Person;
import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

public class SortTests {

    @Test
    public void previousJavaSort(){
        List<Person> persons = PersonFixture.persons();
        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getAge() - p2.getAge();
            }
        });
        persons.forEach(System.out::println);
    }

    @Test
    public void sortTests() {
        List<Person> persons = PersonFixture.persons();
        persons.stream()
                .sorted(comparing(Person::getAge))
                .forEach(System.out::println);
    }

    @Test
    public void sortTestsDesc() {
        List<Person> persons = PersonFixture.persons();
        persons.stream()
                .sorted(comparing(Person::getAge).reversed())
                .forEach(System.out::println);
    }

    @Test
    public void sortMultipleTests() {
        List<Person> persons = PersonFixture.persons();
        persons.stream()
                .sorted(comparing(Person::getAge).thenComparing(Person::getName))
                .forEach(System.out::println);
    }
}
