package com.energizeglobal.internship.cafe;

import com.energizeglobal.internship.cafe.coffee.Coffee;
import com.energizeglobal.internship.cafe.coffee.Espresso;
import com.energizeglobal.internship.cafe.coffee.Latte;
import com.energizeglobal.internship.cafe.coffeeMachines.EspressoMachine;
import com.energizeglobal.internship.cafe.coffeeMachines.LatteMachine;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * We have Cafeteria with 3 coffee machines.
 * Two of them can make espresso and latte, and another one can make only espresso.
 * Need to provide trusty and effective Queue for orders.
 */
public class Cafeteria {

    private final static int ONE_MILLION = 1_000_000;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final LatteMachine latteMachine1 = new LatteMachine();
        final LatteMachine latteMachine2 = new LatteMachine();
        final LatteMachine latteMachine3 = new LatteMachine();
        final EspressoMachine espressoMachine = new EspressoMachine();
        final EspressoMachine espressoMachine2 = new EspressoMachine();
        final OrderTaker waiter = new OrderTaker(latteMachine1, latteMachine2, latteMachine3, espressoMachine, espressoMachine2);
        for (int x = 0; x < 50; x++) {
//            final FutureTask<Latte> latteFutureTask = new FutureTask<>(getLatte(waiter));
            final FutureTask<Espresso> espressoFutureTask = new FutureTask<>(getEspresso(waiter));
//            new Thread(latteFutureTask).start();
//            Thread.sleep(10);
            new Thread(espressoFutureTask).start();
            latteMachine1.getTasksCount()
        }

    }

    private static Callable<Latte> getLatte(OrderTaker orderTaker) {
        return () -> {
            System.out.println("Thread:" + Thread.currentThread().getName() + " needs for latte");
            long startTime = System.nanoTime();
            final Coffee coffee = orderTaker.addOrder("LATTE-1-2-MY!");
            if (coffee instanceof Latte) {
                long endTime = System.nanoTime();
                System.out.println("Thread:" + Thread.currentThread().getName() + " purchases latte. " +
                        "It was " + ((double) (endTime - startTime) / ONE_MILLION) + "ms. in the queue.");
            }
            return (Latte) coffee;
        };
    }

    private static Callable<Espresso> getEspresso(OrderTaker orderTaker) {
        return () -> {
            System.out.println("Thread:" + Thread.currentThread().getName() + " needs for espresso.");
            long startTime = System.nanoTime();
            final Coffee coffee = orderTaker.addOrder("LATTE-1-2-MY!");
            if (coffee instanceof Latte) {
                long endTime = System.nanoTime();
                System.out.println("Thread:" + Thread.currentThread().getName() + " purchases espresso. " +
                        "It was " + ((double) (endTime - startTime) / ONE_MILLION) + "ms. in the queue.");
            }
            return (Espresso) coffee;
        };
    }
}


