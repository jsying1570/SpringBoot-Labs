package cn.iocoder.springboot.lab01.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


/**
 * @author jsyin
 */
@Repository
public class ProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int updateProductStockCountById(Integer stockCount, Long id){
        String sql = "update product_info set stock_count = stock_count - ? where id = ?";
        return jdbcTemplate.update(sql, stockCount, id);
    }
}
