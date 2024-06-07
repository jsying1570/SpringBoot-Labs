package com.example.controller;

import com.example.domain.Account;
import com.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/{id}")
    public Account findAccountById(@PathVariable("id") int id) {
        return accountService.findAccountById(id);
    }


    @GetMapping("/reduceMoney")
    public String reduceMoney(int accountId, double money) {
        accountService.reduceMoney(accountId, money);
        return "success";
    }
}
