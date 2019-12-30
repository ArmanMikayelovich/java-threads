package com.energizeglobal.internship.cafe.coffeeMachines;

import com.energizeglobal.internship.cafe.coffee.Coffee;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class CoffeeMachine {
    protected ExecutorService coffeeMakerExecutorService = Executors.newSingleThreadExecutor();

    public abstract void addTask(Callable<? extends Coffee> coffeeTask);

}
