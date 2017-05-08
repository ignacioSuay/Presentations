package com.ignaciosuay.fp;

import com.ignaciosuay.fp.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonFixture {

    public static List<Person> persons(){
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(31, "Simon"));
        personList.add(new Person(31, "Matt"));
        personList.add(new Person(26, "Natxo"));
        personList.add(new Person(19, "Laura"));
        personList.add(new Person(15, "Johny"));
        personList.add(new Person(50, "Mary"));
        return personList;
    }
}
