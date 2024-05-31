package cn.iocoder.springboot.lab01;

import cn.iocoder.springboot.lab01.config.MainConfig;
import cn.iocoder.springboot.lab01.service.OrderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author salter
 * @version 1.0.0
 */
public class Main {

    public static void main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        OrderService orderService = context.getBean(OrderService.class);
        orderService.submitOrder();
    }
}
