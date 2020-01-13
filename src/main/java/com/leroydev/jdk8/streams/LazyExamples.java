/*
 * Order of execution.
 * Streams are lazy
 */
package com.leroydev.jdk8.streams;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Processing Order
 *
 * @author emaphis
 */
public class LazyExamples {

    public static void main(String[] args) {
        stream01();
        System.out.println("******");
        stream02();
        System.out.println("******");
        stream03();
        System.out.println("******");
        stream04();
        System.out.println("******");
        stream05();
        System.out.println("******");
        stream06();

        stream07();
        stream08();
    }

    // lazy processing.
    static void stream01() {
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter:  " + s);
                    return true;
                }); // doesn't print out.
    }

    // terminating operation forces printout.
    static void stream02() {
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter:  " + s);
                    return true;
                })
                .forEach(s -> System.out.println("foreach: " + s));
    }

    // reduce actuall calls to .map()
    static void stream03() {
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map:     " + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anymatch:" + s);
                    return s.startsWith("A");
                });
    }

    // Why order matters
    static void stream04() {
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map:     " + s);
                    return s.toUpperCase();
                })
                .filter(s -> {
                    System.out.println("filter:  " + s);
                    return s.startsWith("A");
                })
                .forEach(s -> System.out.println("forEach: " + s));
    }

    // Add sorted
    static void stream05() {
        Stream.of("d2", "a2", "b1", "b3", "c")
                .sorted((s1, s2) -> {
                    System.out.printf("sort:  %s; %s\n", s1, s2);
                    return s1.compareTo(s2);
                })
                .filter(s -> {
                    System.out.println("filter:  " + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map:     " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));
    }

    // reorder operations
    static void stream06() {
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter:  " + s);
                    return s.startsWith("a");
                })
                .sorted((s1, s2) -> {
                    System.out.printf("sort:  %s; %s\n", s1, s2);
                    return s1.compareTo(s2);
                })
                .map(s -> {
                    System.out.println("map:     " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));
    }

    // Can't reuse a lazy stream.
    static void stream07() {
        Stream<String> stream =
                Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> s.startsWith("a"));

        stream.anyMatch(s-> true);
       // stream.noneMatch(s -> true); // can't reuse a stream.
    }

    // create a stream supplier
    static void stream08() {
        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));

        streamSupplier.get().anyMatch(s-> true);
        streamSupplier.get().noneMatch(s -> true);

    }

}
