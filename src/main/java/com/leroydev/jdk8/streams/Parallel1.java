/*
 * Parallel Streams.
 */
package com.leroydev.jdk8.streams;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 *
 * @author emaphis
 */
public class Parallel1 {

    public static void main(String[] args) {
        example1();
        System.out.println("********************");
        example2();
        System.out.println("********************");
        example3();
        System.out.println("********************");
        example4();
    }

    static void example1() {
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        System.out.println(commonPool.getParallelism());
    }

    static void example2() {
        Arrays.asList("a1", "a2", "b1", "c2", "c1")
                .parallelStream()
                .filter(s -> {
                    System.out.format("filter:  %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    System.out.format("map:     %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .forEach(s -> {
                    System.out.format("forEach: %s [%s]\n",
                            s, Thread.currentThread().getName());
                });
    }

    static void example3() {
        Arrays.asList("a1", "a2", "b1", "c2", "c1")
                .parallelStream()
                .filter(s -> {
                    System.out.format("filter:  %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    System.out.format("map:     %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .sorted((s1, s2) -> {
                    System.out.format("sort:    %s <> %s [%s]\n",
                        s1, s2, Thread.currentThread().getName());
                    return s1.compareTo(s2);
                })
                .forEach(s -> {
                    System.out.format("forEach: %s [%s]\n",
                            s, Thread.currentThread().getName());
                });
    }

    static void example4() {
        List<Person> persons = Person.getPersons();
        persons
                .parallelStream()
                .reduce(0,
                    (sum, p) -> {
                        System.out.format("accumulator: sum=%s; person=%s [%s]\n",
                            sum, p, Thread.currentThread().getName());
                        return sum += p.age;
                    },
                    (sum1, sum2) -> {
                        System.out.format("combiner:    sum1=%s; sum1=%s     [%s]\n",
                            sum1, sum2, Thread.currentThread().getName());
                        return sum1 + sum2;
                    });
    }
}
