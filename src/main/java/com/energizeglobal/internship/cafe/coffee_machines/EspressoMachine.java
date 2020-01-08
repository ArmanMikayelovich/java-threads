package com.energizeglobal.internship.cafe.coffee_machines;

import com.energizeglobal.internship.cafe.coffee.Coffee;
import com.energizeglobal.internship.cafe.coffee.Espresso;
import com.energizeglobal.internship.cafe.coffee.SugarQuantity;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class EspressoMachine extends CoffeeMachine {

    @Override
    protected Future<? extends Coffee> addTask(Callable<? extends Coffee> coffeeTask) throws InterruptedException {

        concurrentLinkedQueue.add(Objects.requireNonNull(coffeeTask));
        while (true) {
            if (coffeeTask == concurrentLinkedQueue.peek()) {
                concurrentLinkedQueue.poll();
                return coffeeMakerExecutorService.submit(coffeeTask);
            }
            Thread.sleep(100);
        }
    }

    public Espresso makeEspresso(int orderId, int quantity, SugarQuantity sugarQuantity) throws ExecutionException, InterruptedException {
        Callable<Espresso> espressoCallable = () -> {
            Thread.sleep(200);
            return new Espresso(orderId, quantity, sugarQuantity);
        };
        final Future<? extends Coffee> future = addTask(espressoCallable);
        return (Espresso) future.get();
    }

    @Override
    public String toString() {
        return "EspressoMachine " +
                "Task Count : " + getTasksCount();
    }
}
