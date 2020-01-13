/*
 * Reduce examples.
 */
package com.leroydev.jdk8.streams;

import java.util.List;

/**
 *
 * @author emaphis
 */
public class Reduce1 {

    public static void main(String[] args) {
        reduce1();
        System.out.println("***");
        reduce2();
        System.out.println("***");
        reduce3();
        System.out.println("***");
        reduce4();
        System.out.println("***");
        reduce5();
        System.out.println("***");
        reduce6();
    }

    // find person wiht maximum age
    // binary operator
    static void reduce1() {
        List<Person> persons = Person.getPersons();
        persons
            .stream()
            .reduce((p1, p2) -> p1.age > p2.age ? p1 : p2)
            .ifPresent(System.out::println);
    }

    // identity value and a binary operator
    static void reduce2() {
        List<Person> persons = Person.getPersons();
        Person result =
            persons
                .stream()
                .reduce(new Person("",0), (p1, p2) -> {
                    p1.age += p2.age;
                    p1.name += p2.name;
                    return p1;
                });

        System.out.format("name=%s; age=%s\n", result.name, result.age);
    }  // but why?

    //  an identity value, a BiFunction accumulator and
    // a combiner function of type BinaryOperator
    static void reduce3() {
        List<Person> persons = Person.getPersons();
        Integer ageSum = persons
                .stream()
                .reduce(0, (sum, p) -> sum += p.age, (sum1, sum2) -> sum1 + sum2);

        System.out.println("ages: " + ageSum);
    }

    // break down
    static void reduce4() {
        List<Person> persons = Person.getPersons();
        Integer ageSum = persons
            .stream()
            .reduce(0,
                    (sum, p) -> {
                        System.out.format("Accumulator: sum=%s; person=%s\n", sum, p);
                        return sum += p.age;
                    },
                    (sum1, sum2) -> {
                        System.out.format("combinator: sum1=%s; sum2=%s\n", sum1, sum2);
                        return sum1 + sum2;
                    });

        System.out.println("ages: " + ageSum);
    }


    static void reduce5() {
        List<Person> persons = Person.getPersons();
        Integer ageSum = persons
            .parallelStream()
            .reduce(0,
                    (sum, p) -> {
                        System.out.format("Accumulator: sum=%s; person=%s\n", sum, p);
                        return sum += p.age;
                    },
                    (sum1, sum2) -> {
                        System.out.format("combinator: sum1=%s; sum2=%s\n", sum1, sum2);
                        return sum1 + sum2;
                    });

        System.out.println("ages: " + ageSum);
    }

    // more data.
    static void reduce6() {
        List<Person> persons = Person.getPersons();
        Integer ageSum = persons
            .parallelStream()
            .reduce(0,
                    (sum, p) -> {
                        System.out.format("Accumulator: sum=%s; person=%s; thread=%s\n",
                                sum, p, Thread.currentThread().getName());
                        return sum += p.age;
                    },
                    (sum1, sum2) -> {
                        System.out.format("combinator: sum1=%s; sum2=%s; thread=%s\n",
                                sum1, sum2, Thread.currentThread().getName());
                        return sum1 + sum2;
                    });

        System.out.println("ages: " + ageSum);
    }
}
