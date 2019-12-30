package com.energizeglobal.internship.cafe;

import com.energizeglobal.internship.cafe.coffeeMachines.CoffeeMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderTaker {

    private List<? extends CoffeeMachine> coffeeMachines;

    public OrderTaker(CoffeeMachine... machines) {

    }
    private static AtomicInteger ORDER_ID = new AtomicInteger(0);

    private static Integer getOrderId() {
        return ORDER_ID.incrementAndGet();
    }

}
