package com.energizeglobal.internship.cafe.coffee_machines;

import java.util.Comparator;

public class CoffeeMachineQueueComparator<T extends CoffeeMachine> implements Comparator<CoffeeMachine> {

    @Override
    public int compare(CoffeeMachine o1, CoffeeMachine o2) {
        return Integer.compare(o1.getTasksCount(), o2.getTasksCount());
    }
}
