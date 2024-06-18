package com.example.mapper;

import com.example.domain.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface OrderMapper {
    /**
     * 添加订单
     */
    @Insert("insert into t_order values(#{id},#{accountId},#{goodId},#{goodNum},#{orderAmount},#{status})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void addOrder(Order order);
}