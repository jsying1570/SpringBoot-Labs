package com.example.service.impl;

import com.example.domain.Good;
import com.example.mapper.GoodMapper;
import com.example.service.GoodService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
@Slf4j
public class GoodServiceImpl implements GoodService {

    @Autowired
    private GoodMapper goodMapper;

    @Autowired
    DataSource dataSource;


    @Override
    public Good findGoodById(int id) {
        return goodMapper.findGoodById(id);
    }

    @Override
    public void reduceStock(int goodId, int stockNum) {
        log.info("order是否在事务中：{}", RootContext.inGlobalTransaction());
        log.info("全局事务ID：{}", RootContext.getXID());
        log.info("事务模式：{}", RootContext.getBranchType());

        log.info("datasource",dataSource);


        Good good = findGoodById(goodId);
        if (good.getGoodStock() < stockNum) {
            throw new RuntimeException("库存不足");
        }
        goodMapper.reduceStock(goodId, stockNum);
    }
}
