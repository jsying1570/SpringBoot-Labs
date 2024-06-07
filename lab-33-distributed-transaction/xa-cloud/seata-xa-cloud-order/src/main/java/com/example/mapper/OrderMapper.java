package com.example.mapper;

import com.example.domain.Order;
import org.apache.ibatis.annotations.Insert;

public interface OrderMapper {
    /**
     * 添加订单
     */
    @Insert("insert into t_order values(#{id},#{accountId},#{goodId},#{goodNum},#{orderAmount},#{status})")
    void addOrder(Order order);
}