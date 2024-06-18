package com.example.service;

import com.example.domain.Order;
import com.example.mapper.OrderMapper;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void addOrder(Order order) {

        log.info("order是否在事务中：{}", RootContext.inGlobalTransaction());
        log.info("全局事务ID：{}", RootContext.getXID());
        log.info("事务模式：{}", RootContext.getBranchType());

        if (order.getId() == null) {
            order.setId(System.nanoTime());
        }
        orderMapper.addOrder(order);
        System.out.println("orderId:"+order.getId());
    }
}
