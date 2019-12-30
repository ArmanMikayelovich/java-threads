package com.energizeglobal.internship.tests.locks;

public class Synchronization {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            try {
                print();
                printNotSynch();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }

    public static synchronized void print() throws InterruptedException {
        System.out.println();
        long startTime = System.nanoTime();
        String threadCredentials = Thread.currentThread().getName() + ": " + Thread.currentThread().getId();
        System.out.println(threadCredentials);
        Thread.sleep(3);
        long endTime = System.nanoTime();
        System.out.println(threadCredentials + " spent " + ((double) (endTime - startTime) / 1_000_000) +
                "ms for print this section");
    }

    public static  void printNotSynch() throws InterruptedException {
        System.out.println();
        long startTime = System.nanoTime();
        String threadCredentials = Thread.currentThread().getName() + ": " + Thread.currentThread().getId();
        System.out.println(threadCredentials + " without synchronization");
        Thread.sleep(3);
        long endTime = System.nanoTime();
        System.out.println(threadCredentials + " spent " + ((double) (endTime - startTime) / 1_000_000) +
                "ms for print this section without synchronization");
    }
}