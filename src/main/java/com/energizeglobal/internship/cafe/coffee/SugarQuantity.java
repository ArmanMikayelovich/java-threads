package com.energizeglobal.internship.cafe.coffee;

public enum SugarQuantity {
    NO_SUGAR, LESS_SUGAR, NORMAL, MORE_SUGAR;

    public static SugarQuantity fromOrdinal(int ordinal) {
        switch (ordinal) {
            case 0: {
                return SugarQuantity.NO_SUGAR;
            }
            case 1: {
                return SugarQuantity.LESS_SUGAR;
            }
            case 2: {
                return SugarQuantity.NORMAL;
            }
            case 3: {
                return SugarQuantity.MORE_SUGAR;
            }
            default: {
                throw new IllegalStateException("Sugar quantity not allowed");
            }

        }
    }
}
