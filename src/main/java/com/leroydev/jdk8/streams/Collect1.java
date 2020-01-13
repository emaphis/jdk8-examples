/*
 * Advanced Operations
 */
package com.leroydev.jdk8.streams;

import java.util.List;
import java.util.Map;
import java.util.IntSummaryStatistics;
import java.util.StringJoiner;
import java.util.stream.Collector;


/**
 *
 * @author emaphis
 */
import java.util.stream.Collectors;
public class Collect1 {

    public static void main(String[] args) {
        stream1();
        System.out.println("*****");
        stream2();
        System.out.println("*****");
        stream3();
        System.out.println("*****");
        stream4();
        System.out.println("*****");
        stream5();
        System.out.println("*****");
        stream6();
        System.out.println("*****");
        stream7();
    }

    // collect to a list.
    static void stream1() {
        List<Person> persons = Person.getPersons();
        List<Person> filtered =
                persons
                        .stream()
                        .filter(p -> p.name.startsWith("P"))
                        .collect(Collectors.toList());

        System.out.println(filtered);
    }

    // collect to set.
    static void stream2() {
        List<Person> persons = Person.getPersons();
        Map<Integer, List<Person>> personByAge = persons
                .stream()
                .collect(Collectors.groupingBy(p -> p.age));

        personByAge.forEach((age, p) ->
                System.out.format("age %s: %s\n", age, p));
    }

    // collect to primative
    static void stream3() {
        List<Person> persons = Person.getPersons();
        Double averageAge = persons
                .stream()
                .collect(Collectors.averagingInt(p -> p.age));

        System.out.println(averageAge);
    }

    // collect to built in summarizing class.
    static void stream4() {
        List<Person> persons = Person.getPersons();
        IntSummaryStatistics ageSummary =
                persons
                .stream()
                .collect(Collectors.summarizingInt(p -> p.age));

        System.out.println(ageSummary);
    }

    // collect to a string.
    static void stream5() {
        List<Person> persons = Person.getPersons();
        String phrase = persons
                .stream()
                .filter(p -> p.age >= 18)
                .map(p -> p.name)
                .collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));

        System.out.println(phrase);
    }

    static void stream6() {
        List<Person> persons = Person.getPersons();
        Map<Integer, String> map = persons
                .stream()
                .collect(Collectors.toMap(
                        p -> p.age,
                        p -> p.name,
                        (name1,name2) -> name1 + ";" + name2));

        System.out.println(map);
    }

    // Custom collector.
    static Collector<Person, StringJoiner, String> personNameCollector1 =
            Collector.of(
                    () -> new StringJoiner(" | "),          // supplier
                    (j, p) -> j.add(p.name.toUpperCase()),  // accumulator
                    (j1, j2) -> j1.merge(j2),               // combiner
                    StringJoiner::toString);                // finiser

    static void stream7() {
        List<Person> persons = Person.getPersons();
        String names = persons
                .stream()
                .collect(personNameCollector1);

        System.out.println(names);
    }

}
