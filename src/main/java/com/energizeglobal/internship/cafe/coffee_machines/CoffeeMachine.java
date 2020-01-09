package com.energizeglobal.internship.cafe.coffee_machines;

import com.energizeglobal.internship.cafe.coffee.Coffee;

import java.util.Objects;
import java.util.concurrent.*;

public abstract class CoffeeMachine {
    protected ConcurrentLinkedQueue<Callable<? extends Coffee>> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();

    protected ThreadPoolExecutor coffeeMakerExecutorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);

    protected abstract Future<? extends Coffee> addTask(Callable<? extends Coffee> coffeeTask) throws InterruptedException;

    public Integer getTasksCount() {
        return concurrentLinkedQueue.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoffeeMachine that = (CoffeeMachine) o;

        return Objects.equals(coffeeMakerExecutorService, that.coffeeMakerExecutorService);
    }

    @Override
    public int hashCode() {
        return coffeeMakerExecutorService != null ? coffeeMakerExecutorService.hashCode() : 0;
    }


}
