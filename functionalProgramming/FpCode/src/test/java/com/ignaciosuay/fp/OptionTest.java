package com.ignaciosuay.fp;

import io.vavr.collection.List;
import io.vavr.control.Option;
import org.junit.Test;

public class OptionTest {


    @Test
    public void optionTest(){
        List<Option<String>> optionList = List.of(Option.of("a"), Option.none(), Option.of("c"));

        List<String> listString = optionList.flatMap(x -> x);

        assert listString.size() == 2;
    }
}
