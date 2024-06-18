package com.example.service;


import com.example.domain.Good;
import com.example.domain.Order;
import com.example.domain.OrderStatus;
import com.example.feign.AccountFeignClient;
import com.example.feign.GoodFeignClient;
import com.example.feign.OrderFeignClient;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class BusinessService {


  /*  @Autowired
    RestTemplate restTemplate;*/

    @Autowired
    OrderFeignClient orderFeignClient;

    @Autowired
    GoodFeignClient goodFeignClient;

    @Autowired
    AccountFeignClient accountFeignClient;

   /* @GlobalTransactional
    public void placeOrder(int accountId, int goodId, int num) {
        log.info("order是否在事务中：{}", RootContext.inGlobalTransaction());
        log.info("全局事务ID：{}", RootContext.getXID());
        log.info("事务模式：{}", RootContext.getBranchType());

        Good good = restTemplate.getForObject(String.format(Urls.GOOD_INFO, goodId), Good.class);
        double amount = good.getGoodPrice() * num;
        Order order = new Order().setGoodId(goodId).setGoodNum(num).setAccountId(accountId).setOrderAmount(amount).setStatus(OrderStatus.CREATE.getValue());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Order> httpEntity = new HttpEntity<>(order, httpHeaders);
        // 创建订单
        restTemplate.postForObject(Urls.CEATE_ORDER, httpEntity, String.class);
        // 减库存
        restTemplate.getForObject(String.format(Urls.REDUCE_STOCK, goodId, num), Object.class);
        // 减余额
        restTemplate.getForObject(String.format(Urls.REDUCE_MONEY, accountId, amount), Object.class);

    }*/
//    @GlobalTransactional
    public void placeOrder(int accountId, int goodId, int num) {
        log.info("order是否在事务中：{}", RootContext.inGlobalTransaction());
        log.info("全局事务ID：{}", RootContext.getXID());
        log.info("事务模式：{}", RootContext.getBranchType());

        Good good = goodFeignClient.findById(goodId);
        double amount = good.getGoodPrice() * num;
        Order order = new Order().setGoodId(goodId).setGoodNum(num).setAccountId(accountId).setOrderAmount(amount).setStatus(OrderStatus.CREATE.getValue());
        // 创建订单
        orderFeignClient.addOrder(order);
        // 减库存
        goodFeignClient.reduceStock(goodId, num);
        // 减余额
        accountFeignClient.reduceMoney(accountId, amount);
    }
}
