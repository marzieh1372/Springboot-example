package com.example.bank.controller;

import com.example.bank.exceptions.AccountNotFoundException;
import com.example.bank.model.entity.Account;
import com.example.bank.service.AccountServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    private static final Logger LOGGER = LogManager.getLogger(AccountController.class);

    @Autowired
    AccountServiceImpl accountService;

    @PutMapping
    public ResponseEntity updateAccount(Account account){
        LOGGER.info("Starting updateAccount method {" + account.getId()+"}");
        try {
            accountService.updateAccount(account.getId(), account);
            return new ResponseEntity(HttpStatus.OK);
        }catch (AccountNotFoundException e){
            LOGGER.error(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Account> addAccount(@RequestBody Account account){
        LOGGER.info("Starting addAccount method {" + account.getAccountNo() + "}");
        Account savedAccount = accountService.registerAccount(account);
        return new ResponseEntity<Account>(savedAccount, HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable("accountId") Long accountId){
        LOGGER.info("Starting getCustomerById method {" + accountId + "}");
        Account account = accountService.getAccountById(accountId);
        if (account != null) {
            return new ResponseEntity<Account>(account, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity deleteAccount(@PathVariable("accountId") Long accountId){
            LOGGER.info("Starting deleteCustomer method {" + accountId + "}");
        accountService.deleteAccountById(accountId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
