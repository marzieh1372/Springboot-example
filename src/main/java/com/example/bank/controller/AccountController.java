package com.example.bank.controller;

import com.example.bank.api.AccountAPI;
import com.example.bank.exceptions.AccountNotFoundException;
import com.example.bank.model.dto.AccountDto;
import com.example.bank.model.entity.Account;
import com.example.bank.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
public class AccountController implements AccountAPI {


    private ModelMapper modelMapper;

    private final AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public ResponseEntity updateAccount(AccountDto accountDto) {
        log.info("Starting updateAccount method {" + accountDto.getId() + "}");
        try {
            Account accountRequest = modelMapper.map(accountDto, Account.class);
            accountService.updateAccount(accountRequest.getId(), accountRequest);
            AccountDto accountResponse = modelMapper.map(accountRequest, AccountDto.class);
            return new ResponseEntity(HttpStatus.OK);
        } catch (AccountNotFoundException e) {
            log.error(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<AccountDto> addAccount(AccountDto accountDto) {
        log.info("Starting addAccount method {" + accountDto.getAccountNo() + "}");
        Account accountRequest = modelMapper.map(accountDto, Account.class);
        Account savedAccount = accountService.registerAccount(accountRequest);
        AccountDto accountResponse = modelMapper.map(savedAccount, AccountDto.class);
        return new ResponseEntity<AccountDto>(accountResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AccountDto> getAccountById(Long accountId) {
        log.info("Starting getCustomerById method {" + accountId + "}");
        Account account = accountService.getAccountById(accountId);
        if (account != null) {
            AccountDto accountResponse = modelMapper.map(account, AccountDto.class);
            return new ResponseEntity<>(accountResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity deleteAccount(Long accountId) {
        log.info("Starting deleteCustomer method {" + accountId + "}");
        accountService.deleteAccountById(accountId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
