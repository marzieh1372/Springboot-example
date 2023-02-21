package com.example.bank.controller;

import com.example.bank.api.AccountAPI;
import com.example.bank.model.dto.AccountRequest;
import com.example.bank.model.entity.Account;
import com.example.bank.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController

public class AccountController implements AccountAPI {

    @Autowired
    @Qualifier("accountServiceImpl")
    AccountService accountService;

    // private  AccountService accountServiceImpl;

    // public AccountController(AccountService accountService) {
    //  this.accountService = accountService;
    // }

    @Override
    public ResponseEntity updateAccount(Integer accountId, AccountRequest accountRequest) {
        log.info("Updating account by accountId:{}", accountId);
        //try {
        accountService.updateAccount(accountId, accountRequest);
        return new ResponseEntity(HttpStatus.OK);

   /* } catch (AccountNotFoundException ex) {
      log.warn(ex.getMessage());
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/
    }

    @Override
    public ResponseEntity<AccountRequest> addAccount(AccountRequest accountRequest) {
        log.info("Adding new account");
        accountService.registerAccount(accountRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AccountRequest> getAccountById(Integer accountId) {
        log.info("Starting getCustomerById method {" + accountId + "}");
        //try {
        AccountRequest accountResponse = accountService.getAccountById(accountId);

        return new ResponseEntity<>(accountResponse, HttpStatus.OK);

    /*} catch (AccountNotFoundException ex) {
      log.warn(ex.getMessage());
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/
    }

    @Override
    public ResponseEntity deleteAccount(Integer accountId) {
        log.info("Starting deleteCustomer method {" + accountId + "}");
        // try {
        accountService.deleteAccountById(accountId);
        return new ResponseEntity(HttpStatus.OK);
    /*} catch (AccountNotFoundException ex) {
      log.warn(ex.getMessage());
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }*/
    }

    @Override
    public ResponseEntity<List<Account>> getAllAccounts() {
        return new ResponseEntity(accountService.getAllAccount(), HttpStatus.OK);
    }


}
