package com.example.bank.controller;

import com.example.bank.api.AccountAPI;
import com.example.bank.exceptions.AccountNotFoundException;
import com.example.bank.model.dto.AccountRequest;
import com.example.bank.model.entity.Account;
import com.example.bank.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
public class AccountController implements AccountAPI {


    private ModelMapper modelMapper;

    @Autowired
    @Qualifier("accountServiceImpl")
    AccountService accountService;


    //private  AccountService accountServiceImpl;

    //public AccountController(AccountService accountService) {
      //  this.accountService = accountService;
   // }

    @Override
    public ResponseEntity updateAccount(AccountRequest accountRequest) {
        log.info("Starting updateAccount method {" + accountRequest.getId() + "}");
        try {
            Account account = modelMapper.map(accountRequest, Account.class);
            accountService.updateAccount(accountRequest.getId(), account);
            AccountRequest accountResponse = modelMapper.map(accountRequest, AccountRequest.class);
            return new ResponseEntity(HttpStatus.OK);
        } catch (AccountNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<AccountRequest> addAccount(AccountRequest accountRequest) {
        log.info("Starting addAccount method {" + accountRequest.getId() + "}");
        Account account = modelMapper.map(accountRequest, Account.class);
        Account savedAccount = accountService.registerAccount(account);
        AccountRequest accountResponse = modelMapper.map(savedAccount, AccountRequest.class);
        return new ResponseEntity<AccountRequest>(accountResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AccountRequest> getAccountById(Integer accountId) {
        log.info("Starting getCustomerById method {" + accountId + "}");
        Account account = accountService.getAccountById(accountId);
        if (account != null) {
            AccountRequest accountResponse = modelMapper.map(account, AccountRequest.class);
            return new ResponseEntity<>(accountResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity deleteAccount(Integer accountId) {
        log.info("Starting deleteCustomer method {" + accountId + "}");
        accountService.deleteAccountById(accountId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Account>> getAllAccounts() {
        return new ResponseEntity(accountService.getAllAccount(),HttpStatus.OK);
    }
}
