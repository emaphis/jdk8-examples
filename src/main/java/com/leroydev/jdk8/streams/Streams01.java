/*
 * Streams
 */
package com.leroydev.jdk8.streams;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author emaphis
 */
public class Streams01 {

    public static void main(String[] args) {
        List<String> stringCollection = new ArrayList<>();

        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        stringCollection
                .stream()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);

        // sorted
        stringCollection
                .stream()
                .sorted()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);

        System.out.println(stringCollection);

        // map
        stringCollection
                .stream()
                .map(String::toUpperCase)
                .sorted((a, b) -> b.compareTo(a))
                .forEach(System.out::println);

        // Match
        boolean anyStartsWithA =
                stringCollection
                .stream()
                .anyMatch((s) -> s.startsWith("a"));
        System.out.println(anyStartsWithA);

        boolean allStartsWithA =
                stringCollection
                .stream()
                .allMatch((s) -> s.startsWith("a"));
        System.out.println(allStartsWithA);

        boolean noneStartsWithZ =
                stringCollection
                .stream()
                .noneMatch((s) -> s.startsWith("z"));
        System.out.println(noneStartsWithZ);

        // Count
        long startsWithB =
                stringCollection
                .stream()
                .filter((s) -> s.startsWith("b"))
                .count();
        System.out.println(startsWithB);

        // Reduce
        Optional<String> reduced =
                stringCollection
                .stream()
                .sorted()
                .reduce((s1, s2) -> s1 + "s" + s2);
        reduced.ifPresent(System.out::println);

    }
}
