package com.example.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Good {

    private Integer id;

    private String goodName;

    private Double goodPrice;

    private Integer goodStock;
}
