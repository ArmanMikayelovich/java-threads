package com.energizeglobal.internship.cafe.util;

import com.energizeglobal.internship.cafe.coffee.SugarQuantity;
import com.energizeglobal.internship.cafe.coffee_machines.CoffeeMachine;
import com.energizeglobal.internship.cafe.coffee_machines.CoffeeMachineQueueComparator;
import com.energizeglobal.internship.cafe.coffee_machines.LatteMachine;
import com.energizeglobal.internship.cafe.util.exceptions.IllegalCoffeeOrderException;
import com.energizeglobal.internship.cafe.util.exceptions.NoCoffeeMachinesException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderTakerUtil {
    private OrderTakerUtil(){}
    public static String[] getCoffeeParams(String order) {
        String[] params = order.split("-");
        if (params.length == 3 || params.length == 4) {
            if (!params[0].equalsIgnoreCase("ESPRESSO")
                    && !params[0].equalsIgnoreCase("LATTE")) {
                throw new IllegalCoffeeOrderException(order);
            }
            try {
                final int coffeeQuantity = Integer.parseInt(params[1]);
                if (coffeeQuantity < 1 || coffeeQuantity > 3) {
                    throw new IllegalCoffeeOrderException(order);
                }
                final int sugarQuantity = Integer.parseInt(params[2]);
                if (sugarQuantity < 0 || sugarQuantity > SugarQuantity.values().length) {
                    throw new IllegalCoffeeOrderException(order);
                }
            } catch (NumberFormatException ignored) {
                throw new IllegalCoffeeOrderException(order);
            }
        }
        return params;
    }

    public static Set<LatteMachine> getLatteMachines(Collection<? extends CoffeeMachine> coffeeMachines) {
        return coffeeMachines.stream()
                .filter(machine -> machine instanceof LatteMachine)
                .map(coffeeMachine -> (LatteMachine) coffeeMachine)
                .collect(Collectors.toSet());
    }

    public static CoffeeMachine getNonBusyMachine(Set<? extends CoffeeMachine> coffeeMachines) {
        if (coffeeMachines.isEmpty()) {
            throw new NoCoffeeMachinesException();
        }
        CoffeeMachineQueueComparator<? extends CoffeeMachine> comparator = new CoffeeMachineQueueComparator<>();
        final ArrayList<? extends CoffeeMachine> coffeeMachines1 = new ArrayList<>(coffeeMachines);
        coffeeMachines1.sort(comparator);
        return coffeeMachines1.get(coffeeMachines.size() - 1);
    }
}
