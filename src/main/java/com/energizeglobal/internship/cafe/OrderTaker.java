package com.energizeglobal.internship.cafe;

import com.energizeglobal.internship.cafe.coffee.Coffee;
import com.energizeglobal.internship.cafe.coffee.SugarQuantity;
import com.energizeglobal.internship.cafe.coffeeMachines.CoffeeMachine;
import com.energizeglobal.internship.cafe.coffeeMachines.EspressoMachine;
import com.energizeglobal.internship.cafe.coffeeMachines.LatteMachine;
import com.energizeglobal.internship.cafe.util.OrderTakerUtil;
import com.energizeglobal.internship.cafe.util.exceptions.IllegalCoffeeOrderException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderTaker {

    private AtomicInteger ORDER_ID = new AtomicInteger(0);
    private Set<CoffeeMachine> coffeeMachines;

    public OrderTaker(CoffeeMachine... machines) {
        coffeeMachines = new HashSet<>();
        coffeeMachines.addAll(Arrays.asList(machines));
    }

    private Integer getOrderId() {
        return ORDER_ID.incrementAndGet();
    }

    public void addCoffeeMachine(CoffeeMachine coffeeMachine) {
        if (coffeeMachine instanceof LatteMachine) {
            coffeeMachines.add((LatteMachine) coffeeMachine);
        }

    }

    //The OrderMan takes an order notes like this LATTE-1-2-HI!
    // (CoffeeName-quantity-sugarQuantity-coffeeStumpText(ifExists) )
    //                                            ESPRESSO-1-1
    public Coffee addOrder(String coffeeOrder) throws ExecutionException, InterruptedException {

        String[] params = OrderTakerUtil.getCoffeeParams(coffeeOrder);
        if (params.length == 4) {
            final LatteMachine nonBusyMachine = (LatteMachine) OrderTakerUtil
                    .getNonBusyMachine(OrderTakerUtil
                            .getLatteMachines(coffeeMachines));
          return   nonBusyMachine
                    .makeLatte(getOrderId(),
                            Integer.parseInt(params[1]),
                            SugarQuantity.fromOrdinal(Integer.parseInt(params[2])),
                            params[3]);
        } else if (params.length == 3) {
            final EspressoMachine nonBusyMachine = (EspressoMachine) OrderTakerUtil.getNonBusyMachine(coffeeMachines);
            return nonBusyMachine
                    .makeEspresso(getOrderId(),
                            Integer.parseInt(params[1]),
                            SugarQuantity.fromOrdinal(Integer.parseInt(params[2])));
        } else {
            throw new IllegalCoffeeOrderException(coffeeOrder);
        }
    }


}
