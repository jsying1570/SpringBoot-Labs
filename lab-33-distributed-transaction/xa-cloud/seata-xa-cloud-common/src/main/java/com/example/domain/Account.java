package com.example.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Account {

    private Integer id;

    private String name;

    private Double money;
}
