package com.product_management.MS_PRODUCT_MANAGEMENT.domain.enums;

public enum Category {

    ELETRONICOS(1),
    PAPELARIA(2),
    VESTUARIO(3);

    private final int value;

    Category(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
