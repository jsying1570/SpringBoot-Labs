package com.example.service;

import com.example.abc.ABCMapper;
import com.example.icbc.ICBCMapper;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private ABCMapper abcMapper;

    @Autowired
    private ICBCMapper icbcMapper;

    @GlobalTransactional(rollbackFor = Exception.class)
    public void transfer(int fromId, int toId, double money) throws InterruptedException {
        abcMapper.reduceMoney(fromId, money);
//        Thread.sleep(40000);
        int i = 10 / 0;
        icbcMapper.increaseMoney(toId, money);
    }

}
