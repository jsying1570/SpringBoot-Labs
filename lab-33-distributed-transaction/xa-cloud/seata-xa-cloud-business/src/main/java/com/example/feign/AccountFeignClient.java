package com.example.feign;

import com.example.domain.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "seata-xa-account",fallback = AccountFeignClientFallback.class)
public interface AccountFeignClient {

    @GetMapping("/account/{id}")
    Account findAccountById(@PathVariable("id") int id);


    @GetMapping("/account/reduceMoney")
    String reduceMoney(@RequestParam("accountId")int accountId, @RequestParam("money")double money);
}
