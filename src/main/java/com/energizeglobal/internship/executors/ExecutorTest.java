package com.energizeglobal.internship.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorTest {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello " + Thread.currentThread().getName());

        });
        Thread.sleep(100);
        executorService.shutdown();//we need to call shutdown method when want to kill threads in executorService
        //After shutdown the executorService can't accept new tasks
        
        System.out.println("\n Hello: " + Thread.currentThread().getName());
        System.out.println("Is executor terminated:  " + executorService.isTerminated());
        System.out.println("Is executor shutdown:  " + executorService.isShutdown());
        System.out.println("Is executor terminated:  " + executorService.isTerminated());
        System.out.println("Is executor shutdown:  " + executorService.isShutdown());
    }
}
