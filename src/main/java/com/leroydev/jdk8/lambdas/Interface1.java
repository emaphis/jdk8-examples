/*
 * Default and Static Methods for Interfaces.
 */
package com.leroydev.jdk8.lambdas;

/**
 * Default methods in interfaces.
 * @author emaphis
 */
public class Interface1 {

    public interface Formula {
        double calculate(int a);

        // A default method
        default double sqrt(int a) {
            return Math.sqrt(positive(a));
        }

        // A static method - only callable from inside the interface.
        static int positive(int a) {
            return a > 0 ? a : 0;
        }

    }

    public static void main(String[] args) {
        Formula formula1 = new Formula() {
            @Override
            public double calculate(int a) {
                //int ret = positive(a); // impossible
                return sqrt(a * 100);
            }
        };

        System.out.println("calculare(100): " + formula1.calculate(100));
        System.out.println("sqrt(100): " + formula1.sqrt(100));
        System.out.println("sqrt(-23): " + formula1.sqrt(-23));
        System.out.println("positive(-4): " + Formula.positive(-4));

        // Default methods cannot be accessed from within lambda expressions.
        // The following code does not compile:
        // Formula formulat = (a) -> sqrt(a * 100);
    }

}
