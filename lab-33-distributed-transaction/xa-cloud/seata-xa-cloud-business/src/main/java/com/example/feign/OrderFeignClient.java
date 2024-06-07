package com.example.feign;


import com.example.domain.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("seata-xa-order")
public interface OrderFeignClient {

    @PostMapping("/order/addOrder")
    String addOrder(@RequestBody Order order);
}
