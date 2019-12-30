package com.energizeglobal.internship.cafe.coffee;

public abstract class Coffee {
    int orderId;
    private SugarQuantity sugarQuantity;

    public Coffee(int orderId, SugarQuantity sugarQuantity) {
        this.orderId = orderId;
        this.sugarQuantity = sugarQuantity;
    }

    public SugarQuantity getSugarQuantity() {
        return sugarQuantity;
    }
}
