package com.energizeglobal.internship.cafe.coffeeMachines;

import com.energizeglobal.internship.cafe.coffee.Latte;
import com.energizeglobal.internship.cafe.coffee.SugarQuantity;

import java.util.concurrent.Callable;

public class LatteMachine extends EspressoMachine {
    public void makeLatte(int orderId, int quantity, SugarQuantity sugarQuantity, String coffeeStumpText) {
        Callable<Latte> latteCallable = () -> {
            Thread.sleep(400);
            return new Latte(orderId, quantity, sugarQuantity, coffeeStumpText);
        };
        addTask(latteCallable);
    }
}
