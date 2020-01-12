/*
 * A value class.
 */
package com.leroydev.jdk8.lambdas;

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

    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName;
    }

}
