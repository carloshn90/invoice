package com.carlos.invoice.server.enums;

public enum UnitEnum {

    KG("kilogram", 0);

    private String name;
    private int order;

    UnitEnum(String name, int order) {
        this.name = name;
        this.order = order;
    }
}
