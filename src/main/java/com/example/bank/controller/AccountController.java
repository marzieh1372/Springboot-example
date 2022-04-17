package com.example.bank.controller;

import com.example.bank.exceptions.AccountNotFoundException;
import com.example.bank.model.dto.AccountDto;
import com.example.bank.model.entity.Account;
import com.example.bank.service.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    private static final Logger LOGGER = LogManager.getLogger(AccountController.class);

    private ModelMapper modelMapper;

    private final AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PutMapping
    public ResponseEntity updateAccount(AccountDto accountDto) {
        LOGGER.info("Starting updateAccount method {" + accountDto.getId() + "}");
        try {
            Account accountRequest = modelMapper.map(accountDto, Account.class);
            accountService.updateAccount(accountRequest.getId(), accountRequest);
            AccountDto accountResponse = modelMapper.map(accountRequest, AccountDto.class);
            return new ResponseEntity(HttpStatus.OK);
        } catch (AccountNotFoundException e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
        LOGGER.info("Starting addAccount method {" + accountDto.getAccountNo() + "}");
        Account accountRequest = modelMapper.map(accountDto, Account.class);
        Account savedAccount = accountService.registerAccount(accountRequest);
        AccountDto accountResponse = modelMapper.map(savedAccount, AccountDto.class);
        return new ResponseEntity<AccountDto>(accountResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable("accountId") Long accountId) {
        LOGGER.info("Starting getCustomerById method {" + accountId + "}");
        Account account = accountService.getAccountById(accountId);
        if (account != null) {
            AccountDto accountResponse = modelMapper.map(account, AccountDto.class);
            return new ResponseEntity<AccountDto>(accountResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity deleteAccount(@PathVariable("accountId") Long accountId) {
        LOGGER.info("Starting deleteCustomer method {" + accountId + "}");
        accountService.deleteAccountById(accountId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
