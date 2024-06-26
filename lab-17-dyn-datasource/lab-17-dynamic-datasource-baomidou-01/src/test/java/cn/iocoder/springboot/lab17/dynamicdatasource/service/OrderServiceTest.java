package cn.iocoder.springboot.lab17.dynamicdatasource.service;

import cn.iocoder.springboot.lab17.dynamicdatasource.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    /**
     * 源码查看 SpringManagedTransaction这类
     * @throws InterruptedException 中断异常
     */
    @Test
    public void testMethod01() throws InterruptedException {
        orderService.method01();
//        Thread.sleep(1000000);
    }

    @Test
    public void testMethod02() {
        orderService.method02();
    }

    @Test
    public void testMethod03() {
        orderService.method03();
    }

    @Test
    public void testMethod04() {
        orderService.method04();
    }

    @Test
    public void testMethod05() {
        orderService.method05();
    }

}
