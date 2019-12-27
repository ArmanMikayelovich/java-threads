package com.energizeglobal.com;

public class Basics {
    static final Thread.UncaughtExceptionHandler UNCAUGHT_EXCEPTION_HANDLER = getOurUncaughtExceptionHandler();


    private static Thread.UncaughtExceptionHandler getOurUncaughtExceptionHandler() {
        return (t, e) -> System.out.println("Thread Id: " + t.getId() + ": " + t.getName() + " : " + e.getMessage());
    }
    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().setUncaughtExceptionHandler(UNCAUGHT_EXCEPTION_HANDLER);
//        throw new RuntimeException("This is a message.");
        testInterrupting(UNCAUGHT_EXCEPTION_HANDLER);
        testUncaughtExceptionHandler();
        System.out.println("In main method");
    }

    private static void testUncaughtExceptionHandler() {

        ThreadGroup parentOfAllThreadGroups = getHighLevelThreadGroup();
        Thread.currentThread().setUncaughtExceptionHandler(UNCAUGHT_EXCEPTION_HANDLER);
        throw new RuntimeException("This is a message.");
    }

    private static ThreadGroup getHighLevelThreadGroup() {
         ThreadGroup tempThreadGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup threadGroup = tempThreadGroup;
        while (tempThreadGroup != null) {
            threadGroup = tempThreadGroup;
            tempThreadGroup = tempThreadGroup.getParent();
        }
        return threadGroup;
    }

    private static void testInterrupting(Thread.UncaughtExceptionHandler handler) throws InterruptedException {
        Runnable runnable = () -> {
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
            int x = 0;
            try {
                Thread.currentThread().sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            throw new RuntimeException(Thread.currentThread().getName() + Thread.currentThread().getId());
//            while (!Thread.currentThread().isInterrupted()) {
//
//                System.out.println("Count: " + x++ + " ||  Interrupted flag is: " + Thread.currentThread().isInterrupted());
//            }
//            System.out.println("Thread interrupted.");
        };

        final Thread thread = new Thread(runnable);

        thread.setUncaughtExceptionHandler(UNCAUGHT_EXCEPTION_HANDLER);
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        thread.start();
        thread.interrupt();
        thread.sleep(1);
        Thread.sleep(1);
        thread.interrupt();
        thread.stop();

        Thread.currentThread().setPriority(Thread.NORM_PRIORITY);

    }
}
