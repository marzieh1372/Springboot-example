package com.example.bank.controller;

import com.example.bank.exceptions.AccountNotFoundException;
import com.example.bank.exceptions.AmountRestrictionException;
import com.example.bank.model.dto.AccountDepositDto;
import com.example.bank.service.AccountServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/bank/account")
public class BankController {

    //TODO change responseEntity to general Object


    private static final Logger LOGGER = LogManager.getLogger(BankController.class);

    @Autowired
    private AccountServiceImpl accountService;
    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/{accountId}/balance")
    public ResponseEntity getBalance(@PathVariable("accountId") String accountNo){
        LOGGER.debug("Starting getBalance method {"+accountNo+"}");
        try {
            return new ResponseEntity<BigDecimal>(accountService.getBalance(accountNo), HttpStatus.OK);
        } catch (AccountNotFoundException e) {
            return new ResponseEntity(null,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity depositToAccount(@RequestBody AccountDepositDto accountDepositDto){
        LOGGER.debug("Starting getBalance method {"+accountDepositDto.getAccountNo()+"}");
        try {
            return  new ResponseEntity<BigDecimal>(accountService.AddDeposit(accountDepositDto),HttpStatus.OK);
        } catch (AccountNotFoundException e) {
            //TODO return generic object when we have exception contain errMsg,errCode
            return new ResponseEntity(null,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/withdrawal")
    public ResponseEntity withdrawal(@RequestBody AccountDepositDto accountDepositDto){
        LOGGER.debug("Starting withdrawal method {"+accountDepositDto.getAccountNo()+" }");
        try {
            return new ResponseEntity<BigDecimal>(accountService.withdrawal(accountDepositDto),HttpStatus.OK);
        } catch (AccountNotFoundException e) {
            return new ResponseEntity(null,HttpStatus.NOT_FOUND);
        }catch (AmountRestrictionException ex){
            return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
