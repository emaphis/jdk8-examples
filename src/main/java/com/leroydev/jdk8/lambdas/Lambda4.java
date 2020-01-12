/*
 * Build-in Funtional interfaces.
 */
package com.leroydev.jdk8.lambdas;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.Consumer;
import java.util.Comparator;
import java.util.UUID;
import java.util.concurrent.Callable;

/**
 *
 * @author emaphis
 */
public class Lambda4 {

    public static void main(String[] args) throws Exception  {
        // Predicates
        Predicate<String> predicate = (s) -> s.length() > 0;

        boolean one = predicate.test("foo");
        boolean two = predicate.negate().test("foo");
        System.out.println(one + " : " + two);

        Predicate<String> nonNull = Objects::nonNull;
        Predicate<String> isNull = Objects::isNull;

        one = nonNull.test("");
        two = nonNull.test(null);
        System.out.println(one + " : " + two);
        one = isNull.test("");
        two = isNull.test(null);
        System.out.println(one + " : " + two);

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();

        one = isEmpty.test("");
        two = isEmpty.test("not empty");
        System.out.println(one + " : " + two);
        one = isNotEmpty.test("");
        two = isNotEmpty.test("not empty");
        System.out.println(one + " : " + two);

        // Functions.
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<Integer, String> toString = String::valueOf;
        Function<String, String> backToString = toInteger.andThen(toString);

        String str1 = backToString.apply("123");
        System.out.println(str1);

        // Suppliers
        Supplier<Person> personSupplier = Person::new;
        Person pr = personSupplier.get();
        System.out.println(pr);

        // Consumers
        Consumer<Person> greeter =
                (p) -> System.out.println("Hello, " + p.firstName);
        greeter.accept(new Person("Luke", "Skywalker"));

        // Comparators
        Comparator<Person> comparator =
                (p1, p2) -> p1.firstName.compareTo(p2.firstName);

        Person p1 = new Person("John", "Doe");
        Person p2 = new Person("Alice", "Wonderland");
        Person p3 = new Person("John", "Studd");

        int num1 = comparator.compare(p1, p2);            // > 0
        int num2 = comparator.reversed().compare(p1, p2); // < 0
        int num3 = comparator.compare(p1, p3);            // == 0
        System.out.println(num1 + " " + num2 + " " + num3);

        // Runnabls
        Runnable runnable = () -> System.out.println(UUID.randomUUID());
        runnable.run();

        // Callable
        Callable<UUID> callable = UUID::randomUUID;
        UUID uuid = callable.call();
        System.out.println(uuid);
    }

}
