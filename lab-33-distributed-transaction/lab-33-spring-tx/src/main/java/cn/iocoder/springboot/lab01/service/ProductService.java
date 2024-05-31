package cn.iocoder.springboot.lab01.service;

import cn.iocoder.springboot.lab01.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateProductStockCountById(Integer stockCount, Long id){
        productDao.updateProductStockCountById(stockCount, id);
        int res = 1 / 0;
    }
}
