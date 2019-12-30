package com.energizeglobal.internship.cafe.coffee;

public class Espresso extends Coffee {
    int quantity;

    public Espresso(int orderId, int quantity, SugarQuantity sugarQuantity) {
        super(orderId, sugarQuantity);
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
