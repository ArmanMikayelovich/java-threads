package com.energizeglobal.internship.cafe.util.exceptions;

public class NoCoffeeMachinesException extends RuntimeException {
    public NoCoffeeMachinesException() {
    }

    public NoCoffeeMachinesException(String message) {
        super(message);
    }
}
