package com.ignaciosuay.fp;


import org.junit.Test;

import static io.vavr.API.*;
import static org.junit.Assert.assertEquals;

public class PatternMatchingTests {

    @Test
    public void testDataPatternMatching(){

        int input = 2;
        String output = Match(input).of(
                Case($(1), "one"),
                Case($(2), "two"),
                Case($(3), "three"),
                Case($(), "?"));

        assertEquals("two", output);
    }

    @Test
    public void testDataPM(){

//        LocalDate now = LocalDate.now();
//        String of = Match(now).of(
//                Case(LocalDate($()), () -> "2016-02-13"),
////                Case(LocalDate(2016, $(), $()), d -> d.toString()),
//                Case($(), () -> "error")
////                Case(LocalDate(2017, 2 , 14), m -> "aa")
//        );
//
//        System.out.println(of);

    }
}
