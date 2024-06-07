package com.example.domain;

public enum OrderStatus {
    CREATE("创建");


    private String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
