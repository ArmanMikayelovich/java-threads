package com.energizeglobal.internship.cafe.coffee;

public class Latte extends Espresso {
    private String coffeeStumpText;

    public String getCoffeeStumpText() {
        return coffeeStumpText;
    }

    public Latte(int orderId, int quantity, SugarQuantity sugarQuantity, String coffeeStumpText) {
        super(orderId, quantity, sugarQuantity);
        this.coffeeStumpText = coffeeStumpText;
    }

}
