package com.example.bank.service;

import com.example.bank.exceptions.AccountNotFoundException;
import com.example.bank.model.dto.Deposit;
import com.example.bank.model.dto.Withdrawal;
import com.example.bank.model.entity.Account;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Qualifier("accountServiceIranImpl")
@Service
public class AccountServiceIranImpl implements AccountService{
    @Override
    public List<Account> getAllAccount() {
        return null;
    }

    @Override
    public Account getAccountById(Integer id) {
        return null;
    }

    @Override
    public Account registerAccount(Account account) {
        return null;
    }

    @Override
    public Account updateAccount(Integer id, Account account) throws AccountNotFoundException {
        return null;
    }

    @Override
    public void deleteAccountById(Integer id) {

    }

    @Override
    public Double getBalance(Integer accountId) throws AccountNotFoundException {
        return null;
    }

    @Override
    public Double addDeposit(Integer accountId, Deposit deposit) throws AccountNotFoundException {
        return null;
    }

    @Override
    public Double withdrawal(Integer accountId, Withdrawal withdrawal) throws AccountNotFoundException {
        return null;
    }
}
