package com.example.bank.controller;

import com.example.bank.exceptions.AccountNotFoundException;
import com.example.bank.exceptions.AccountRestrictionException;
import com.example.bank.model.dto.AccountDepositDto;
import com.example.bank.service.AccountService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/bank/account")
@Slf4j
public class BankController {

    private ModelMapper modelMapper;

    private AccountService accountService;
    public BankController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping("/{accountId}/balance")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable("accountId") String accountNo){
        log.info("Starting getBalance method {" + "}");
        try{
            return new ResponseEntity<BigDecimal>(accountService.getBalance(accountNo), HttpStatus.OK);
        } catch (AccountNotFoundException e){
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity depositToAccount(@PathVariable("accountId") AccountDepositDto accountDepositDto){
        log.info("Starting depositToAccount method {" + accountDepositDto.getAccountNo() +"}");
        try {
            return new ResponseEntity<BigDecimal>(accountService.addDeposit(accountDepositDto), HttpStatus.OK);
        }catch (AccountNotFoundException e){
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{accountId}/withdrawal")
    public ResponseEntity withdrawal(@PathVariable("accountId") AccountDepositDto accountDepositDto){
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
