package com.energizeglobal.internship.tests.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Contidtions {
    static final ReentrantLock REENTRANT_LOCK = new ReentrantLock();
    static final Condition CONDITION = REENTRANT_LOCK.newCondition();
    static volatile int COUNT = 0;

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            REENTRANT_LOCK.lock();
            System.out.println("Thread name: " + Thread.currentThread().getName() +
                    ": Thread ID : " + Thread.currentThread().getId());
            while (COUNT < 5) {
                System.out.println("Thread" + Thread.currentThread().getId() + "waiting for condition");
                try {
                    CONDITION.await();
                } catch (InterruptedException e) {
                    CONDITION.signalAll();
                    e.printStackTrace();
                }
            }
            System.out.println("Thread" + Thread.currentThread().getId() + ": " + COUNT);
        };

        Runnable incrementer = () -> {
            REENTRANT_LOCK.lock();
            while (COUNT < 5) {
                System.out.println(Thread.currentThread().getId() + " increments count. Count now: " + COUNT);
                COUNT++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            CONDITION.signalAll();
            REENTRANT_LOCK.unlock();
        };

        new Thread(runnable).start();
        Thread.sleep(100);
        new Thread(incrementer).start();

    }
}
