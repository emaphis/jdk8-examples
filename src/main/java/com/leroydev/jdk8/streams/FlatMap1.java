/*
 * Flatmap on streams.
 */
package com.leroydev.jdk8.streams;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.IntStream;


/**
 *
 * @author emaphis
 */
public class FlatMap1 {
    public static void main(String[] args) {
        stream1();
        System.out.println("*****");
        stream2();
    }

    static class Foo {
        String name;
        List<Bar> bars = new ArrayList<>();

        Foo(String name) {
            this.name = name;
        }
    }

    static class Bar {
        String name;

        Bar(String name) {
            this.name = name;
        }
}

    static void stream1() {
        List<Foo> foos = new ArrayList<>();

        // create foos
        IntStream
                .range(1, 4)
                .forEach(i -> foos.add(new Foo("Foo" + i)));

        // create bars for each foo.
        foos.forEach(f ->  IntStream
                .range(1, 4)
                .forEach(i -> f.bars.add(new Bar("Bar" + i + " <- " + f.name))));

        foos.stream()
                .flatMap(f -> f.bars.stream())
                .forEach(b -> System.out.println(b.name));

    }

    static void stream2() {
        IntStream.range(1, 4)
                .mapToObj(i -> new Foo("Foo" + i))
                .peek(f -> IntStream.range(1, 4)
                        .mapToObj(i -> new Bar("Bar" + i + " <- " + f.name))
                        .forEach(f.bars::add))
                .flatMap(f -> f.bars.stream())
                .forEach(b -> System.out.println(b.name));
    }

}
