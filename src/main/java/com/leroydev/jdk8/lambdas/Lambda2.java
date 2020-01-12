/*
 * Functional Intefaces
 */
package com.leroydev.jdk8.lambdas;

/**
 *
 * @author emaphis
 */
public class Lambda2 {

    // A functional interface
    @FunctionalInterface
    public static interface Converter<F, T> {
        T convert(F from);
    }

    static class Something{
        String startsWith(String s) {
            return String.valueOf(s.charAt(0));
        }
    }

    interface PersonFactory<P extends Person> {
        P create(String firstName, String lastName);
    }

    public static void main(String[] args) {
        Converter<String, Integer> integerConverter1 = (from) -> Integer.valueOf(from);
        Integer converted1 = integerConverter1.convert("123");
        System.out.println(converted1);

        // Method refereces.
        Converter<String, Integer> integerConverter2 = Integer::valueOf;
        Integer converted2 = integerConverter2.convert("123");
        System.out.println(converted2);

        // A string converter.
        Something something = new Something();
        Converter<String, String> stringConverter = something::startsWith;
        String converted3 = stringConverter.convert("Java");
        System.out.println(converted3);

        // Constructor reference.
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");
        System.out.println(person);
    }

}
