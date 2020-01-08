package com.energizeglobal.internship.cafe;

import com.energizeglobal.internship.cafe.coffee.Coffee;
import com.energizeglobal.internship.cafe.coffee.Espresso;
import com.energizeglobal.internship.cafe.coffee.Latte;
import com.energizeglobal.internship.cafe.coffee_machines.EspressoMachine;
import com.energizeglobal.internship.cafe.coffee_machines.LatteMachine;
import com.energizeglobal.internship.cafe.util.exceptions.WrongCoffeeException;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * We have Cafeteria with 3 coffee machines.
 * Two of them can make espresso and latte, and another one can make only espresso.
 * Need to provide trusty and effective Queue for orders.
 */
public class Cafeteria {

    private static final int ONE_MILLION = 1_000_000;

    public static void main(String[] args) {
        final LatteMachine latteMachine1 = new LatteMachine();
        final LatteMachine latteMachine2 = new LatteMachine();
        final EspressoMachine espressoMachine = new EspressoMachine();
        final OrderTaker waiter = new OrderTaker(latteMachine1, latteMachine2);
        waiter.addCoffeeMachine(espressoMachine);
        for (int x = 0; x < 5; x++) {
            final FutureTask<Latte> latteFutureTask = new FutureTask<>(getLatte(waiter));
            final FutureTask<Espresso> espressoFutureTask = new FutureTask<>(getEspresso(waiter));
            new Thread(latteFutureTask).start();
            new Thread(espressoFutureTask).start();
            latteMachine1.getTasksCount();
        }

    }

    private static Callable<Latte> getLatte(OrderTaker orderTaker) {
        return () -> {
            System.out.println(getCurrentThreadName() + " needs for latte");
            long startTime = System.nanoTime();
            final Coffee coffee = orderTaker.addOrder("LATTE-1-2-MY!");
            if (coffee instanceof Latte) {
                long endTime = System.nanoTime();
                System.out.println(getCurrentThreadName() + " purchases latte. " +
                        "It was " + ((double) (endTime - startTime) / ONE_MILLION) + "ms. in the queue.");
                System.out.println(getCurrentThreadName() + coffee.toString());
                return (Latte) coffee;
            }
            throw new WrongCoffeeException("Needs an latte, but was given " + coffee.getClass());
        };
    }

    private static Callable<Espresso> getEspresso(OrderTaker orderTaker) {
        return () -> {
            System.out.println(getCurrentThreadName() + " needs for espresso.");
            long startTime = System.nanoTime();
            final Coffee coffee = orderTaker.addOrder("ESPRESSO-1-2");
            if (coffee instanceof Espresso) {
                long endTime = System.nanoTime();
                System.out.println(getCurrentThreadName() + " purchases espresso. " +
                        "It was " + ((double) (endTime - startTime) / ONE_MILLION) + "ms. in the queue.");
                System.out.println(getCurrentThreadName() + coffee.toString());
                return (Espresso) coffee;
            }
            throw new WrongCoffeeException("Needs an espresso, but was given " + coffee.getClass());
        };
    }



    private static String getCurrentThreadName() {
        return "Thread:" + Thread.currentThread().getName();
    }
}


