package com.energizeglobal.internship.cafe.coffeeMachines;

import com.energizeglobal.internship.cafe.coffee.Coffee;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public abstract class CoffeeMachine {
    protected ThreadPoolExecutor coffeeMakerExecutorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);

    public abstract Future<? extends Coffee> addTask(Callable<? extends Coffee> coffeeTask);

    public Integer getTasksCount() {
        return coffeeMakerExecutorService.getQueue().size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoffeeMachine that = (CoffeeMachine) o;

        return coffeeMakerExecutorService != null ? coffeeMakerExecutorService.equals(that.coffeeMakerExecutorService) : that.coffeeMakerExecutorService == null;
    }

    @Override
    public int hashCode() {
        return coffeeMakerExecutorService != null ? coffeeMakerExecutorService.hashCode() : 0;
    }


}
