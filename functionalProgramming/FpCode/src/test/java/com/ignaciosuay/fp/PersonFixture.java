package com.ignaciosuay.fp;

import com.ignaciosuay.fp.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonFixture {

    public static List<Person> persons() {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Simon", 31));
        personList.add(new Person("Matt", 31));
        personList.add(new Person("Natxo", 26));
        personList.add(new Person("Laura", 19));
        personList.add(new Person("Johny", 15));
        personList.add(new Person("Mary", 50));
        return personList;
    }
}
