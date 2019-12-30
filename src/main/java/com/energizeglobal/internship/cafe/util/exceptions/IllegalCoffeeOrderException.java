package com.energizeglobal.internship.cafe.util.exceptions;

public class IllegalCoffeeOrderException extends RuntimeException {
    public IllegalCoffeeOrderException() {
    }

    public IllegalCoffeeOrderException(String message) {
        super(message);
    }
}
