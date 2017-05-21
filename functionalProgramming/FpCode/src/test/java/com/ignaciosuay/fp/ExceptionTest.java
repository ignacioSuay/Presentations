package com.ignaciosuay.fp;


import io.vavr.CheckedFunction1;
import io.vavr.collection.Stream;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;


public class ExceptionTest {

    @Before
    public void createFile() throws IOException {
        List<String> lines = Arrays.asList("Hey you!");
        Path file = Paths.get("log.txt");
        Files.write(file, lines, Charset.forName("UTF-8"));
    }

    @Test
    public void testPath() {

        Function<String, String> readLine = path -> {
            try {
                return new BufferedReader(new FileReader(path)).readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        };

        System.out.println(readLine.apply("log.txt"));

        CheckedFunction1<String, String> line = path -> new BufferedReader(new FileReader(path)).readLine();

        System.out.println(readLine.apply("loga.txt"));

    }

    @Test
    public void testFileException() {
        Stream.of("log.txt", "NoExist.txt")
                .map(path -> {
                    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)))) {
                        return bufferedReader.readLine();
                    } catch (Exception e) {
                        return e.getMessage();
                    }
                })
                .forEach(System.out::println);
    }

    @Test(expected = RuntimeException.class)
    public void testWrapFileException() {
        Stream.of("log.txt", "NoExist.txt")
                .map(path -> {
                    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)))) {
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
                .map(path ->
                        Try.of(() -> new BufferedReader(new FileReader(path)).readLine())
                                .getOrElse(() -> "File doesn't exist"))
                .forEach(System.out::println);

        Stream.of("log.txt", "NoExist.txt")
                .map(path -> Try.of(() -> new BufferedReader(new FileReader(path)).readLine()))
                .filter(Try::isSuccess)
                .forEach(tryLine -> System.out.println(tryLine.get()));


    }

    @Test(expected = FileNotFoundException.class)
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
                .map(this::eitherLineOrException)
                .map(e -> e.getOrElseGet(Throwable::getMessage))
                .forEach(System.out::println);
    }

    private Either<IOException, String> eitherLineOrExceptionOld(String path) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            return Either.right(bufferedReader.readLine());
        } catch (IOException exception) {
            return Either.left(exception);
        }
    }

    private Either<Throwable, String> eitherLineOrException(String path) {
        return Try.of(() -> new BufferedReader(new FileReader(path)).readLine()).toEither();
    }
}