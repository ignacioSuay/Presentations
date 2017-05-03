package com.ignaciosuay.fp;

import javaslang.control.Either;
import javaslang.control.Try;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;


public class ExceptionTest {

    @Before
    public void createFile() throws IOException {
        List<String> lines = Arrays.asList("Hey you!");
        Path file = Paths.get("log.txt");
        Files.write(file, lines, Charset.forName("UTF-8"));
    }

    @Test
    public void testFileException() {
        ClassLoader classLoader = getClass().getClassLoader();
        Stream.of("log.txt", "NoExist.txt")
                .map(path -> {
                    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)))){
                        return bufferedReader.readLine();
                    } catch (Exception e) {
                        return e.getMessage();
                    }
                })
                .forEach(System.out::println);
    }

    @Test(expected = IOException.class)
    public void testWrapFileException() {
        Stream.of("log.txt", "NoExist.txt")
                .map(path -> {
                    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)))){
                        return bufferedReader.readLine();
                    } catch (Exception e) {
                        throw new RuntimeException("File doesn't exist");
                    }
                })
                .forEach(System.out::println);
    }

    @Test
    public void testVavrFileException() {
        Stream.of("log.txt", "NoExist.txt")
                .map(path -> Try.of(() -> new BufferedReader(new FileReader(path))))
                .peek(f -> f.onFailure(f2 -> System.out.println("File does not exist")))
                .filter(Try::isSuccess)
                .forEach(System.out::println);
    }

    private Try<BufferedReader> newFile(String path) {
        return Try.of(() -> new BufferedReader(new FileReader(path)));
    }

    @Test(expected = Try.NonFatalException.class)
    public void testVavrThrowException() {
        Stream.of("log.txt", "NoExist.txt")
                .map(path -> Try.of(() -> new BufferedReader(new FileReader(path))))
                .map(br -> Try.of(() -> br.get().readLine()))
                .map(Try::get)
                .forEach(System.out::println);
    }

    //Use Either when you would like to get more information from the exception
    // to propagate the error is better to use Try
    @Test
    public void usingEither() {
        Stream.of("log.txt", "NoExist.txt")
                .map(this::readLine)
                .map(e->e.getOrElseGet(Throwable::getMessage))
                .forEach(System.out::println);

    }

    private Either<IOException, String> readLine(String path) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            return Either.right(bufferedReader.readLine());
        } catch (IOException exception) {
            return Either.left(exception);
        }
    }
}
