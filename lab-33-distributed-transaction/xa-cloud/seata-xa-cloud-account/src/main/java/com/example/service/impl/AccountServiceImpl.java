package com.example.service.impl;

import com.example.domain.Account;
import com.example.mapper.AccountMapper;
import com.example.service.AccountService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;


    @Override
    public Account findAccountById(int id) {
        return accountMapper.findAccountById(id);
    }

    @Override
    public void reduceMoney(int accountId, double money) {
        log.info("order是否在事务中：{}", RootContext.inGlobalTransaction());
        log.info("全局事务ID：{}", RootContext.getXID());
        log.info("事务模式：{}", RootContext.getBranchType());

        Account account = findAccountById(accountId);
        if (account.getMoney() < money) {
            throw new RuntimeException("余额不足");
        }
        accountMapper.reduceMoney(money, accountId);
    }
}
