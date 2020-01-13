/*
 * Using flatmap and optional to avoid null reference errors.
 */
package com.leroydev.jdk8.streams;

import java.util.Optional;
import java.util.function.Supplier;

class Outer {
    Nested nested = new Nested();
    Nested getNested() {
        return nested;
    }
}

class Nested {
    Inner inner = new Inner();
    Inner getInner() {
        return inner;
    }
}

class Inner {
    String foo = "foo";
    String getFoo() {
        return foo;
    }
}

/**
 *
 * @author emaphis
 */
public class Optional2 {

    public static void main(String[] args) {
        example1();
        System.out.println("****");
        example2();
        System.out.println("****");
        example3();
        System.out.println("****");
        example4();
    }

    // testing for nulls
    static void example1() {
        Outer outer = new Outer();
        if (outer != null && outer.nested != null && outer.nested.inner != null) {
            System.out.println(outer.nested.inner.foo);
        }
    }

    // using optionals
    static void example2() {
        Optional.of(new Outer())
                .map(Outer::getNested)
                .map(Nested::getInner)
                .map(Inner::getFoo)
                .ifPresent(System.out::println);
    }

    // useing a resolver
    public static <T> Optional<T> resolve(Supplier<T> resolver) {
        try {
            T result = resolver.get();
            return Optional.ofNullable(result);
        }
        catch (NullPointerException ex) {
            return Optional.empty();
        }
    }

    static void example3() {
        Outer obj = new Outer();
        resolve(() -> obj.getNested().getInner().getFoo())
                .ifPresent(System.out::println);
    }

    // using flatmap
    static void example4() {
        Optional.of(new Outer())
                .flatMap(o -> Optional.ofNullable(o.nested))
                .flatMap(n -> Optional.ofNullable(n.inner))
                .flatMap(i -> Optional.ofNullable(i.foo))
                .ifPresent(System.out::println);
    }
}
