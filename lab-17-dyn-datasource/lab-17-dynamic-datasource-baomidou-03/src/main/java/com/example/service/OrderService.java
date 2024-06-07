package com.example.service;

import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.example.dataobject.OrderDO;
import com.example.dataobject.UserDO;
import com.example.mapper.OrderMapper;
import com.example.mapper.UserMapper;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;

    private OrderService self() {
        return (OrderService) AopContext.currentProxy();
    }

    @DSTransactional
    public void method01() {
        // 查询订单
        OrderDO orderDO = new OrderDO();
        orderDO.setId(1);
        orderDO.setUserId(2);
        int res = orderMapper.insert(orderDO);

        // 查询用户
        UserDO user = userMapper.selectById(1);
        System.out.println(user);
    }
}
