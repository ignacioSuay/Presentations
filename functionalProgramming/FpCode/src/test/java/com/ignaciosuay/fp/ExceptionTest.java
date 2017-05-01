package com.ignaciosuay.fp;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

public class ExceptionTest {

    @Test
    public void testFileException() {
        Stream.of("/usr","/tmp"​)
                .map(path -> {
                    try {
                        return new File(path).getCanonicalPath();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                })
                .forEach(System.out::println);

//                .map(path -> ​new​ ​File​(path).getCanonicalPath()).forEach(​System​.out::println);​

    }
}
