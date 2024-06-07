package com.example.dataobject;

import lombok.Getter;
import lombok.Setter;

/**
 * 订单 DO
 */
@Getter
@Setter
public class OrderDO {
    /**
     * 订单编号
     */
    private Integer id;
    /**
     * 用户编号
     */
    private Integer userId;
}
