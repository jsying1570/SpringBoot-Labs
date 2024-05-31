package cn.iocoder.springboot.lab01.service;

import cn.iocoder.springboot.lab01.dao.OrderDao;
import cn.iocoder.springboot.lab01.dao.ProductDao;
import cn.iocoder.springboot.lab01.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * @author jsyin
 */
@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductService productService;

    @Transactional(rollbackFor = Exception.class)
    public void submitOrder() {
        //生成订单
        Order order = new Order();
        long number = Math.abs(new Random().nextInt(500));
        order.setId(number);
        order.setOrderNo("order_" + number);
        orderDao.saveOrder(order);
        productService.updateProductStockCountById(1,1L);
//        productDao.updateProductStockCountById(1, 1L);
//        int i = 1 / 0;
        //减库存
//        this.updateProductStockCountById(1, 1L);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateProductStockCountById(Integer stockCount, Long id) {
        productDao.updateProductStockCountById(stockCount, id);
        int i = 1 / 0;
    }


}
