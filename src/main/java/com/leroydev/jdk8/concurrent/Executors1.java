/*
 * The executor service
 */
package com.leroydev.jdk8.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.sound.midi.SysexMessage;

/**
 *
 * @author emaphis
 */
public class Executors1 {

    public static void main(String[] args) {
        //example1(1);
        example1(7);  // fail state
    }

    static void example1(long seconds) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(seconds);
                String name = Thread.currentThread().getName();
                System.out.println("Hello " + name);
            }
            catch (InterruptedException e) {
                System.err.println("task interrupted");
            }
        });
        stop(executor);
    }

    static void stop(ExecutorService executor) {
        try {
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("termination interrupted");
        }
        finally {
            if (!executor.isTerminated()) {
                System.err.println("killing non-finsed tasks!!");
            }
            executor.shutdownNow();
            System.out.println("shutdown fiished");
        }
    }
}


