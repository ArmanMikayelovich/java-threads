package com.energizeglobal.internship.cafe.coffee_machines;

import com.energizeglobal.internship.cafe.coffee.Coffee;
import com.energizeglobal.internship.cafe.coffee.Latte;
import com.energizeglobal.internship.cafe.coffee.SugarQuantity;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class LatteMachine extends EspressoMachine {

    public Latte makeLatte(int orderId, int quantity, SugarQuantity sugarQuantity, String coffeeStumpText) throws ExecutionException, InterruptedException {
        Callable<Latte> latteCallable = () -> {
            Thread.sleep(350);
            return new Latte(orderId, quantity, sugarQuantity, coffeeStumpText);
        };
        final Future<? extends Coffee> future = addTask(latteCallable);
        return (Latte) future.get();
    }
}
