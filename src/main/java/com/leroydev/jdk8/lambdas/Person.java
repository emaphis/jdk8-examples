/*
 * A value class.
 */
package com.leroydev.jdk8.lambdas;

import java.util.Arrays;
import java.util.List;

/**
 * @author emaphis
 */
public class Person {
    public String firstName;
    public String lastName;

    public Person() {
        this.firstName = "Who";
        this.lastName = "Cares";
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean isLegalName(String name) {
        return name.length() > 3 && name.length() < 20;
    }

    public static boolean isRealPerson(Person p) {
        return p.firstName.length() + p.lastName.length() > 0;
    }

    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName;
    }

    static List<Person> getPersonlist() {
        return Arrays.asList(
                new Person("Max", "Paine"),
                new Person("Peter", "Parker"),
                new Person("Pamela", "Stevens"),
                new Person("David", "McKinley"));
    }

}
