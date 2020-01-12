/*
 * Parallel Streams
 */

package com.leroydev.jdk8.streams;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 *
 * @author emaphis
 */
public class Stream02 {

    public static final int MAX = 1000000;

    public static void sortSequential() {
        List<String> values = new ArrayList<>();
        for (int i = 0; i < MAX; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        // Sequential

        long t0 = System.nanoTime();

        long count = values.stream().sorted().count();
        System.out.println(count);

        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("Sequential sort took: %d ms", millis));
    }

    public static void sortParallel() {
        List<String> values = new ArrayList<>();
        for (int i = 0; i < MAX; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        // Parallel

        long t0 = System.nanoTime();

        long count = values.parallelStream().sorted().count();
        System.out.println(count);

        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("Sequential sort took: %d ms", millis));
    }

    public static void main(String[] args) {
       sortSequential();
       sortParallel();
    }

}
