package com.example.service;

import com.example.domain.Account;

public interface AccountService {
    Account findAccountById(int id);

    void reduceMoney(int accountId, double money);
}
