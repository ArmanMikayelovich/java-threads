package com.energizeglobal.internship.cafe.coffeeMachines;

import com.energizeglobal.internship.cafe.coffee.Coffee;
import com.energizeglobal.internship.cafe.coffee.Espresso;
import com.energizeglobal.internship.cafe.coffee.SugarQuantity;

import java.util.concurrent.Callable;

public class EspressoMachine extends CoffeeMachine {

    @Override
    public void addTask(Callable<? extends Coffee> coffeeTask) {
        coffeeMakerExecutorService.submit(coffeeTask);
    }

    public void makeEspresso(int orderId, int quantity, SugarQuantity sugarQuantity) {
        Callable<Espresso> espressoCallable = () -> {
            Thread.sleep(200);
            return new Espresso(orderId, quantity, sugarQuantity);
        };
        addTask(espressoCallable);
    }

}
