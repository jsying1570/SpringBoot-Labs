package cn.iocoder.springboot.lab01.dao;

import cn.iocoder.springboot.lab01.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


/**
 * @author jsyin
 */
@Repository
public class OrderDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int saveOrder(Order order){
        String sql = "insert into order_info (id, order_no) values (?, ?)";
        return jdbcTemplate.update(sql, order.getId(), order.getOrderNo());
    }
}
