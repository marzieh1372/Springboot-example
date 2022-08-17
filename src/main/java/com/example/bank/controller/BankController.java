package com.example.bank.controller;

import com.example.bank.api.BankAPI;
import com.example.bank.exceptions.AccountNotFoundException;
import com.example.bank.exceptions.AccountRestrictionException;
import com.example.bank.model.dto.Deposit;
import com.example.bank.model.dto.Withdrawal;
import com.example.bank.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class BankController implements BankAPI {

    @Autowired
    @Qualifier("accountServiceImpl")
    AccountService accountService;

    @Override
    public ResponseEntity<Double> getBalance(Integer accountId) {
        log.info("Starting getBalance method {" + accountId + "}");
        try {
            return new ResponseEntity<Double>(accountService.getBalance(accountId), HttpStatus.OK);
        } catch (AccountNotFoundException e) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity depositToAccount(Integer accountId, Deposit deposit) {
        log.info("Starting depositToAccount method {" + accountId + "}");
        try {
            return new ResponseEntity<Double>(
                    accountService.addDeposit(accountId, deposit), HttpStatus.OK);
        } catch (AccountNotFoundException e) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity withdrawal(Integer accountId, Withdrawal withdrawal) {
        log.info("Starting withdrawal method {" + accountId + "}");
        try {
            return new ResponseEntity<Double>(
                    accountService.withdrawal(accountId, withdrawal), HttpStatus.OK);
        } catch (AccountNotFoundException e) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        } catch (AccountRestrictionException ex) {
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        log.debug("Gole sangam****************");
        Map<String, String> map = new HashMap<>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach(
                        (error) -> {
                            map.put(error.getCode(), error.getDefaultMessage());
                        });
        return map;
    }
}
