package com.example.domain;

import lombok.Getter;

@Getter
public enum OrderStatus {
    CREATE("create");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

}
