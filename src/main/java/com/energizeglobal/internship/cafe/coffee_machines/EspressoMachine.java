package com.energizeglobal.internship.cafe.coffee_machines;

import com.energizeglobal.internship.cafe.coffee.Coffee;
import com.energizeglobal.internship.cafe.coffee.Espresso;
import com.energizeglobal.internship.cafe.coffee.SugarQuantity;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class EspressoMachine extends CoffeeMachine {

    @Override
    public Future<? extends Coffee> addTask(Callable<? extends Coffee> coffeeTask) {
        return coffeeMakerExecutorService.submit(coffeeTask);
    }

    public Espresso makeEspresso(int orderId, int quantity, SugarQuantity sugarQuantity) throws ExecutionException, InterruptedException {
        Callable<Espresso> espressoCallable = () -> {
            Thread.sleep(200);
            return new Espresso(orderId, quantity, sugarQuantity);
        };
        return (Espresso) addTask(espressoCallable).get();
    }

}
