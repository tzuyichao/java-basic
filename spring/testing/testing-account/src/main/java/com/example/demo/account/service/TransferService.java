package com.example.demo.account.service;

import com.example.demo.account.domain.Account;

import java.math.BigDecimal;

public interface TransferService {
    void transferMoney(long idSender, long idReceiver, BigDecimal amount);
    Iterable<Account> getAllAccounts();
    Iterable<Account> findAccountsByName(String name);
}
