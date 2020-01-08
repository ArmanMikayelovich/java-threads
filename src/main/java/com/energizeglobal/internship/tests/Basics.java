package com.energizeglobal.internship.tests;

public class Basics {
    static final Thread.UncaughtExceptionHandler UNCAUGHT_EXCEPTION_HANDLER = getOurUncaughtExceptionHandler();


    private static Thread.UncaughtExceptionHandler getOurUncaughtExceptionHandler() {
        return (t, e) -> System.out.println("Thread Id: " + t.getId() + ": " + t.getName() + " : " + e.getMessage());
    }
    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().setUncaughtExceptionHandler(UNCAUGHT_EXCEPTION_HANDLER);
        testInterrupting();
        testUncaughtExceptionHandler();
        System.out.println("In main method");
    }

    private static void testUncaughtExceptionHandler() {

        Thread.currentThread().setUncaughtExceptionHandler(UNCAUGHT_EXCEPTION_HANDLER);
        throw new RuntimeException("This is a message.");
    }


    private static void testInterrupting() throws InterruptedException {
        Runnable runnable = () -> {
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
            try {
                Thread.currentThread().sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            throw new RuntimeException(Thread.currentThread().getName() + Thread.currentThread().getId());

        };

        final Thread thread = new Thread(runnable);

        thread.setUncaughtExceptionHandler(UNCAUGHT_EXCEPTION_HANDLER);
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        thread.start();
        thread.interrupt();
        thread.sleep(1);
        Thread.sleep(1);
        thread.interrupt();
        Thread.currentThread().setPriority(Thread.NORM_PRIORITY);

    }
}
