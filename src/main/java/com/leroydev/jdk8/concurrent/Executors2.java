/*
 * Callables and Futures
 */
package com.leroydev.jdk8.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author emaphis
 */
public class Executors2 {

    public static void main(String[] args)
        throws ExecutionException, InterruptedException {
        //example1();
        example2();
    }

    static void example1() throws InterruptedException, ExecutionException {
        Callable<Integer> task = () -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                return 123;
            } catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future = executor.submit(task);

        System.out.println("future done? " + future.isDone());

        Integer result = future.get();

        System.out.println("future done? " + future.isDone());
        System.out.println("result: " + result);

        executor.shutdown();
    }

    // Timeout
    static void example2()
            throws InterruptedException, ExecutionException {

        Callable<Integer> task = () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                return 123;
            }
            catch (InterruptedException ex) {
                throw new IllegalStateException("task iterrupted", ex);
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future = executor.submit(task);

        try {
            Integer result = future.get(2, TimeUnit.SECONDS);
            System.out.println("result: " + result);
        }
        catch (TimeoutException e) {
            System.err.println(e.toString());
        }

        executor.shutdown();
    }
}
