package com.example.bank.service;

import com.example.bank.exceptions.AccountNotFoundException;
import com.example.bank.model.entity.Account;

import java.util.List;


public interface AccountService {

    List<Account> getAllAccount();
    Account getAccountById(Long id);
    Account registerAccount(Account account);
    Account updateAccount(Long id, Account account) throws AccountNotFoundException;
    void deleteAccountById(Long id);
}
