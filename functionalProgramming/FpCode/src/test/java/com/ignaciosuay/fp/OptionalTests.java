package com.ignaciosuay.fp;

import io.vavr.collection.Stream;
import io.vavr.control.Option;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OptionalTests {

    @Test
    public void optionalTest(){
        List<Optional<String>> optionalList = Arrays.asList(Optional.of("a"), Optional.empty(), Optional.of("c"));

        List<String> listString = optionalList.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        assert listString.size() == 2;
    }
}
