package com.ignaciosuay.fp;

import javaslang.collection.Stream;
import org.junit.Test;


/**
 * Created by suaymasi on 03/05/2017.
 */
public class ReduceTest {

    @Test
    public void reduceTest() {
        Integer result = Stream.of(5, 2, 3)
                .reduce((x, y) -> x + y);
        assert result == 10;
    }

    @Test
    public void foldLeftTest() {
        Integer result = Stream.of(5, 2, 3)
                .fold(0, (x, y) -> (x + y) * x);
        assert result == 0;
        System.out.println(result);

    }

    @Test
    public void foldRightTest() {
        Integer result = Stream.of(5, 2, 3)
                .foldRight(0, (x, y) -> (x + y) * x);
        System.out.println(result);
        assert result == 135;
    }


}
