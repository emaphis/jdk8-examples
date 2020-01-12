/*
 * Lambda expressions.
 */
package com.leroydev.jdk8.lambdas;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Lambda examples
 * @author emaphis
 */
public class Lambda1 {

    public static void main(String[] args) {
        List<String> names1 = Arrays.asList("Charley", "Sabria", "Bosley", "Zeno");

        // pre-jdk8
        Collections.sort(names1, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return a.compareTo(b);
            }
        });

        System.out.println("names1: " + names1);

        List<String> names2 = Arrays.asList("Charley", "Sabria", "Bosley", "Zeno");

        // jdk8 - lambdas
        Collections.sort(names2, (a, b) -> a.compareTo(b));
        System.out.println("names2: " + names2);

        List<String> names3 = Arrays.asList("Charley", null, "Sabria", "Bosley", "Zeno");
        names3.sort(Comparator.nullsLast(String::compareTo));
        System.out.println("names3: " + names3);
    }
}
