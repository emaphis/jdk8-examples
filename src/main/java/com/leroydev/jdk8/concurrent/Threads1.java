/*
 * Threads and Runnables
 */
package com.leroydev.jdk8.concurrent;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author emaphis
 */
public class Threads1 {

    public static void main(String[] args) {
       // example1();
        System.out.println("***");
       // example2();
        //System.out.println("***");
        example3();
    }

    // Threads
    static void example1() {
        Runnable task = () -> {
            String name = Thread.currentThread().getName();
            System.out.println("Hello " + name);
        };


        Thread thread = new Thread(task);
        thread.start();

        System.out.println("Done!");
    }

    // Sleep
    static void example2() {
        Runnable runnable = () -> {
            try {
                System.out.println("Foo " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Bar " + Thread.currentThread().getName());
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

    // Thread Sleep
    static void example3() {
        Runnable runnable = () -> {
            try {
                System.out.println("Foo " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Bar " + Thread.currentThread().getName());
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

}
