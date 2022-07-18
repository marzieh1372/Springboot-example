package com.example.bank.controller;

import com.example.bank.api.BankAPI;
import com.example.bank.exceptions.AccountNotFoundException;
import com.example.bank.exceptions.AccountRestrictionException;
import com.example.bank.model.dto.AccountDepositDto;
import com.example.bank.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@Slf4j
public class BankController implements BankAPI {

    private ModelMapper modelMapper;

    private AccountService accountService;
    public BankController(AccountService accountService){
        this.accountService = accountService;
    }

    @Override
    public ResponseEntity<BigDecimal> getBalance(AccountDepositDto AccountDepositDto){
        log.info("Starting getBalance method {" + AccountDepositDto.getAccountNo() +"}");
        try{
            return new ResponseEntity<BigDecimal>(accountService.getBalance(AccountDepositDto.getAccountId()), HttpStatus.OK);
        } catch (AccountNotFoundException e){
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity depositToAccount(AccountDepositDto accountDepositDto){
        log.info("Starting depositToAccount method {" + accountDepositDto.getAccountNo() +"}");
        try {
            return new ResponseEntity<BigDecimal>(accountService.addDeposit(accountDepositDto), HttpStatus.OK);
        }catch (AccountNotFoundException e){
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity withdrawal(AccountDepositDto accountDepositDto){
        log.info("Starting withdrawal method {" + accountDepositDto.getAccountNo() + "}");
        try{
            return new ResponseEntity<BigDecimal>(accountService.withdrawal(accountDepositDto), HttpStatus.OK);
        }catch (AccountNotFoundException e){
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }catch (AccountRestrictionException ex){
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
