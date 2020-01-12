/*
 * Maps.
 */
package com.leroydev.jdk8.misc;

import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author emaphis
 */
public class Maps1 {

    public static void main(String[] args) {
        // new mothods for jdk8
        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }

        map.forEach((id, val) -> System.out.println(val));

        map.computeIfPresent(3, (num, val) -> val + num);
        String str = map.get(3);
        System.out.println(str);


        // compute on a map using functions.

        str = map.computeIfPresent(9, (num, val) -> null);
        boolean b1 = map.containsKey(9);  // false
        System.out.println("str:" + str + " b1:" + b1);

        str = map.computeIfAbsent(23, num -> "val" + num);
        b1 = map.containsKey(23);    // true
        System.out.println("str:" + str + " b1:" + b1);

        str = map.computeIfAbsent(3, num -> "bam");
        String str2 = map.get(3);             // val33
        System.out.println("str:" + str + " str2:" +str2);

        System.out.println(map);

        // mapulating values only if they exist.

        map.remove(3, "val3");
        map.get(3);             // val33

        map.remove(3, "val33");
        map.get(3);             // null

        map.getOrDefault(42, "not found");

        System.out.println(map);

        map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
        map.get(9);             // val9

        map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
        map.get(9);             // val9concat
        System.out.println(map);
    }

}
