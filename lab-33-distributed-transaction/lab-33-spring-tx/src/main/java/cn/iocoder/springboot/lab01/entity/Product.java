
package cn.iocoder.springboot.lab01.entity;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class Product {

    /**
     * 数据id
     */
    private Long id;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品价格
     */
    private BigDecimal productPrice;

    /**
     * 库存数量
     */
    private Integer stockCount;
}
