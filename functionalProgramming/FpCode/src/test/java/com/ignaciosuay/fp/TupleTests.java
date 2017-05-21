package com.ignaciosuay.fp;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.junit.Test;

public class TupleTests {

    @Test
    public void tupleTest() {

        Tuple2<Integer, String> tuple = Tuple.of(1, "a");
        assert tuple._1 == 1;
        assert tuple._2 == "a";

        Tuple2<Integer, String> tuple2 = tuple.map(k -> k + 1, v -> v);

        assert tuple2._1 == 2;
        assert tuple2._2 == "a";

        String apply = tuple.apply((k, v) -> k + v);
        assert apply.equals("1a");
    }

}
