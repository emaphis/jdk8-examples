/*
 * A value class.
 */
package com.leroydev.jdk8.streams;

import java.util.List;
import java.util.Arrays;

/**
 * @author emaphis
 */
public class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name;
    }

    static List<Person> getPersons() {
        return Arrays.asList(
                new Person("Max", 18),
                new Person("Peter", 23),
                new Person("Pamela", 23),
                new Person("David", 12));
    }

}
