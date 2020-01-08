package com.energizeglobal.internship.cafe.coffee;

public class Latte extends Espresso {
    private String coffeeStumpText;


    public Latte(int orderId, int quantity, SugarQuantity sugarQuantity, String coffeeStumpText) {
        super(orderId, quantity, sugarQuantity);
        this.coffeeStumpText = coffeeStumpText;
    }

    @Override
    public String toString() {
        return super.toString() +
                "coffeeStumpText='" + coffeeStumpText+"'";
    }
}
