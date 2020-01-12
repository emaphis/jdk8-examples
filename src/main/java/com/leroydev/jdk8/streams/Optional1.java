/*
 * Optional
 */
package com.leroydev.jdk8.streams;

import java.util.Optional;

/**
 *
 * @author emaphis
 */
public class Optional1 {

    public static void main(String[] args) {
        // Optionals
        Optional<String> optional1 = Optional.of("bam");
        //Optional<String> optional2 = Optional.of(null);

        boolean one = optional1.isPresent();
        String str1 = optional1.get();
        String str2 = optional1.orElse("default");
        System.out.println(one + " " + str1 + " " + str2);

        optional1.ifPresent((s) -> System.out.println(s.charAt(0)));

        //one = optional1.isPresent();
        //str2 = optional2.orElse("default");
        //System.out.println(one + " " + str2);
    }
}
