/*
 * Method References.
 */
package com.leroydev.jdk8.lambdas;

import java.util.List;

/**
 *
 * @author emaphis
 */
public class MethodReference {

    public static void main(String[] args) {

        // Reference to a static method.
        List<com.leroydev.jdk8.lambdas.Person> list = Person.getPersonlist();
        // Old method.
        boolean isReal1 = list.stream().anyMatch(p -> Person.isRealPerson(p));
        // JDK 8 method reference.
        boolean isReal2 = list.stream().anyMatch(Person::isRealPerson);

        System.out.println(isReal1 + " : " + isReal2);

        // TODO:  wont compile.
        // Reference to an Instance Method.
        //Person max = list.get(0);
        //boolean name = list.stream().anyMatch(max::isLegalName);
        //System.out.println("name: " + name);

    }

}
