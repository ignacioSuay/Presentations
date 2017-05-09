package com.ignaciosuay.fp;

import com.ignaciosuay.fp.model.Person;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.ignaciosuay.fp.PersonFixture.persons;

public class CollectTests {

    @Test
    public void collectForEach() {
        List<Person> personOlderThan30 = new ArrayList<>();
        persons().stream()
                .filter(p -> p.getAge() > 30)
                .forEach(personOlderThan30::add);

        personOlderThan30.forEach(System.out::println);
    }

    @Test
    public void collectTest() {
        List<Person> personOlderThan30 = persons().stream()
                .filter(p -> p.getAge() > 30)
                .collect(Collectors.toList());

        personOlderThan30.forEach(System.out::println);
    }

    @Test
    public void collectCustomTest() {
        List<Person> personOlderThan30 = persons().stream()
                .filter(p -> p.getAge() > 30)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        personOlderThan30.forEach(System.out::println);
    }

    @Test
    public void collectCustomJsonArrayTest() {
        JSONArray personOlderThan30 = persons().stream()
                .filter(p -> p.getAge() > 30)
                .map(JSONObject::new)
                .collect(JSONArray::new, JSONArray::put, JSONArray::put);

        System.out.println(personOlderThan30.toString());
    }

}
