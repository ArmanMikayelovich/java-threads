package com.energizeglobal.internship.cafe.coffeeMachines;

import com.energizeglobal.internship.cafe.coffee.Latte;
import com.energizeglobal.internship.cafe.coffee.SugarQuantity;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class LatteMachine extends EspressoMachine {
    public Latte makeLatte(int orderId, int quantity, SugarQuantity sugarQuantity, String coffeeStumpText) throws ExecutionException, InterruptedException {
        Callable<Latte> latteCallable = () -> {
            Thread.sleep(400);
            return new Latte(orderId, quantity, sugarQuantity, coffeeStumpText);
        };
        return (Latte) addTask(latteCallable).get();
    }
}
