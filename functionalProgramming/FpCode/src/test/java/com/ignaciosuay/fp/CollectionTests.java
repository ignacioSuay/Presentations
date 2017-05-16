package com.ignaciosuay.fp;

import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import org.junit.Test;

import java.util.Arrays;

public class CollectionTests {

    @Test
    public void listTest(){
        List<Integer> list = List.of(1, 2, 3);
        assert 2 == list.get(1);

        List<Integer> list2 = list.append(4);
        assert 4 == list2.get(3);

        java.util.List<Integer> javaList = Arrays.asList(3, 2, 1);
        List<Integer> javaToJavaslang = List.ofAll(javaList);
        assert 3 == javaToJavaslang.get(0);
    }

    @Test
    public void mapTest(){
        Map<String, String> map = HashMap.of("a", "1", "b", "2");
        assert map.size() == 2;

        map.put("c", "3");
        assert map.size() == 2;

        Map<String, String> map2 = map.put("c", "3");
        assert map2.size() == 3;

        map2.keySet().forEach(System.out::println);
    }
}
