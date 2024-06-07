package com.example.feign;

import com.example.domain.Account;
import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.spring.annotation.GlobalTransactional;
import io.seata.tm.api.GlobalTransactionContext;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Component
public class AccountFeignClientFallback implements AccountFeignClient{


    @Override
    public Account findAccountById(int id) {
        return null;
    }

    @Override
    public String reduceMoney(int accountId, double money) {
        if(RootContext.inGlobalTransaction()) {
            try {
                GlobalTransactionContext.reload(RootContext.getXID()).rollback();
            } catch (TransactionException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("reduceMoney 方法降级 ！！！！");
        return null;
    }
}
