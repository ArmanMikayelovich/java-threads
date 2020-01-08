package com.energizeglobal.internship.cafe.coffee;

public abstract class Coffee {
    private int orderId;
    private SugarQuantity sugarQuantity;

    public Coffee(int orderId, SugarQuantity sugarQuantity) {
        this.orderId = orderId;
        this.sugarQuantity = sugarQuantity;
    }

    @Override
    public String toString() {
        return "Coffee " +
                "orderId=" + orderId +
                ", sugarQuantity= " + sugarQuantity;
    }
}
