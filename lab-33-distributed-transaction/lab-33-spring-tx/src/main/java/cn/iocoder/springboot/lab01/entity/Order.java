package cn.iocoder.springboot.lab01.entity;

import lombok.Data;

@Data
public class Order {

    /**
     * 数据id
     */
    private Long id;

    /**
     * 订单编号
     */
    private String orderNo;

}
