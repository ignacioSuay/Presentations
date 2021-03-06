package com.ignaciosuay.fp;


import io.vavr.Function0;
import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.Function3;
import io.vavr.control.Option;
import org.junit.Test;

import java.util.function.Function;

public class FunctionTests {

    Function2<Integer, Integer, Integer> sum = (x, y) -> x + y;

    @Test
    public void curried() {
        Function1<Integer, Function1<Integer, Integer>> sumCurried = sum.curried();
        Function1<Integer, Integer> sum10 = sumCurried.apply(10);
        assert sum10.apply(5) == 15;


        Function3<Integer, Integer, Integer, Integer> sum3 = (x, y, z) -> x + y + z;
        Function1<Integer, Function1<Integer, Function1<Integer, Integer>>> curried3 = sum3.curried();
        Function1<Integer, Function1<Integer, Integer>> sum25 = curried3.apply(25);
        assert sum25.apply(2).apply(3) == 30;

    }

    @Test
    public void memoization(){
        Function2<Integer, Integer, Integer> sum = (x, y) -> x + y;
        Function2<Integer, Integer, Integer> sumMemoized = sum.memoized();
        assert sumMemoized.apply(2).apply(3) == 5;
        assert sumMemoized.apply(2).apply(3) == 5;

        //Don't use it when you expect different values:
        Function0<Double> randomMemoized= Function0.of(Math::random).memoized();
        Double random1 = randomMemoized.apply();
        Double random2 = randomMemoized.apply();
        assert random1 == random2;
    }

    @Test
    public void lift(){
        // You can lift a partial function into a total function that returns an Option result
        Function2<Integer, Integer, Integer> divide = (a, b) -> a / b;
        Function2<Integer, Integer, Option<Integer>> safeDivide = Function2.lift(divide);

        assert Option.of(2).equals(safeDivide.apply(8,4));
        assert Option.none().equals(safeDivide.apply(8, 0));
    }

    @Test
    public void function3(){
        Function<Integer, Function<Integer, Function<Integer, Integer>>> sum3Curried = x -> y -> z -> x + y + z;

        assert 10 == sum3Curried.apply(2).apply(3).apply(5);

    }

}
