package com.example.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Order {

    private Long id;
    private Integer accountId;
    private Integer goodId;
    private Integer goodNum;
    private Double orderAmount;
    private String status;

}
