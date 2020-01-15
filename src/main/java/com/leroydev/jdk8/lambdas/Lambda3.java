/*
 * Lambda scopes.
 */
package com.leroydev.jdk8.lambdas;

/**
 *
 * @author emaphis
 */
public class Lambda3 {

    @FunctionalInterface
    public static interface Converter<F, T> {
        T convert(F from);
    }

    static int outerStaticNum;

    int outerNum;

    void testScopes() {

        // Accessing local variables.
        final int num = 1;

        Converter<Integer, String> stringConverter =
                (from) -> String.valueOf(from + num);

        String convert = stringConverter.convert(2);
        System.out.println(convert);

        Converter<Integer, String> stringConverter2 =
                (from) -> {
                    outerNum = 13;
                    return String.valueOf(from);
                };

        String convert2 = stringConverter2.convert(3);
        System.out.println(convert2);

        String[] array = new String[1];
        Converter<Integer, String> stringConverter3 =
                (from) -> {
                    array[0] = "High there";
                    return String.valueOf(from);
                };

        String str3 = stringConverter3.convert(23);
        System.out.println(str3);
        System.out.println(array[0]);
    }

    public static void main(String[] args) {
        new Lambda3().testScopes();
    }
}
