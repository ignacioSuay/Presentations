package com.ignaciosuay.fp;

import com.ignaciosuay.fp.model.Person;
import org.junit.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static com.ignaciosuay.fp.PersonFixture.persons;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toList;

public class GroupByTests {

    @Test
    public void groupByOldTest() {
        Map<Integer, List<Person>> hashMap = new HashMap<>();
        List<Person> persons = persons();
        for(Person person : persons) {
            if (!hashMap.containsKey(person.getAge())) {
                List<Person> list = new ArrayList<>();
                list.add(person);
                hashMap.put(person.getAge(), list);
            } else {
                hashMap.get(person.getAge()).add(person);
            }
        }

        System.out.println(hashMap);
    }


    @Test
    public void groupByTest() {
        Map<Integer, List<Person>> personOlderThan30 = persons().stream()
                .filter(p -> p.getAge() > 30)
                .collect(Collectors.groupingBy(Person::getAge));

        // {50=[Person(age=50, name=Mary)],
        // 31=[Person(age=31, name=Simon), Person(age=31, name=Matt)]}
        System.out.println(personOlderThan30);
    }

    @Test
    public void groupByMappingTest() {
        Map<Integer, List<String>> personOlderThan30 = persons().stream()
                .filter(p -> p.getAge() > 30)
                .collect(Collectors.groupingBy(Person::getAge, mapping(Person::getName, toList())));

        // {50=[Mary], 31=[Simon, Matt]}
        System.out.println(personOlderThan30);
    }

    @Test
    public void groupByAgeandGetMaxName() {
        Map<Integer, Optional<Person>> personOlderThan30 = persons().stream()
                .filter(p -> p.getAge() > 30)
                .collect(Collectors.groupingBy(Person::getAge,
                        reducing(BinaryOperator.maxBy(comparing(Person::getName)))));

//        {50=Optional[Person(age=50, name=Mary)], 31=Optional[Person(age=31, name=Simon)]}
        System.out.println(personOlderThan30);
    }
}
