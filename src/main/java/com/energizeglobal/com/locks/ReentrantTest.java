package com.energizeglobal.com.locks;

import com.sun.org.apache.regexp.internal.RE;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantTest {
    final static ReentrantLock REENTRANT_LOCK = new ReentrantLock();

    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println("Thread name: " + Thread.currentThread().getName() +
                    ": Thread ID : " + Thread.currentThread().getId());
            REENTRANT_LOCK.lock();
//            REENTRANT_LOCK.lock(); if current thread calls lock method twice,
//            it need to call unlock method it TWICE!!!
            System.out.println("Thread " + Thread.currentThread().getId() +
                    " acquired lock of the REENTRANT_LOCK " + REENTRANT_LOCK.getHoldCount() + " times.");
            System.out.println("Thread" + Thread.currentThread().getId() + "calculates sum of 5 and 6");
            System.out.println("Thread" + Thread.currentThread().getId() + ": " + (5 + 6));
            REENTRANT_LOCK.unlock();
        };
        new Thread(runnable).start();
        new Thread(runnable).start();

    }
}
