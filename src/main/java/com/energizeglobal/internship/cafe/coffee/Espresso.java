package com.energizeglobal.internship.cafe.coffee;

public class Espresso extends Coffee {
    private int quantity;

    public Espresso(int orderId, int quantity, SugarQuantity sugarQuantity) {
        super(orderId, sugarQuantity);
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return super.toString()+
                " quantity=" + quantity;
    }
}
