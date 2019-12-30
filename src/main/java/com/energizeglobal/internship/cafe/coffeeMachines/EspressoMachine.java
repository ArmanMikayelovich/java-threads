package com.energizeglobal.internship.cafe.coffeeMachines;

import com.energizeglobal.internship.cafe.coffee.Coffee;
import com.energizeglobal.internship.cafe.coffee.Espresso;
import com.energizeglobal.internship.cafe.coffee.SugarQuantity;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class EspressoMachine extends CoffeeMachine {

    @Override
    public Future<? extends Coffee> addTask(Callable<? extends Coffee> coffeeTask) {
        final Future<? extends Coffee> submit = coffeeMakerExecutorService.submit(coffeeTask);
        return submit;
    }

    public Espresso makeEspresso(int orderId, int quantity, SugarQuantity sugarQuantity) throws ExecutionException, InterruptedException {
        Callable<Espresso> espressoCallable = () -> {
            Thread.sleep(1000);
            return new Espresso(orderId, quantity, sugarQuantity);
        };
        return (Espresso) addTask(espressoCallable).get();
    }

}
