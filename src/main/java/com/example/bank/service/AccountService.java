package com.example.bank.service;

import com.example.bank.exceptions.AccountNotFoundException;
import com.example.bank.model.dto.Deposit;
import com.example.bank.model.dto.Withdrawal;
import com.example.bank.model.entity.Account;

import java.math.BigDecimal;
import java.util.List;


public interface AccountService {

    List<Account> getAllAccount();
    Account getAccountById(Integer id);
    Account registerAccount(Account account);
    Account updateAccount(Integer id, Account account) throws AccountNotFoundException;
    void deleteAccountById(Integer id);
    Double getBalance(Integer accountId) throws AccountNotFoundException;
    Double addDeposit(Integer accountId, Deposit deposit) throws AccountNotFoundException;
    Double withdrawal(Integer accountId, Withdrawal withdrawal) throws AccountNotFoundException;
}
