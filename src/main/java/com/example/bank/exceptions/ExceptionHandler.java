package com.example.bank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMsg handleAccountNotFoundException(RuntimeException ex, 
                                                                   WebRequest request){
        //............TODO
        ErrorMsg errorMsg = new ErrorMsg(ex.getMessage(),new Date());
        
       // return new ResponseEntity(errorMsg, HttpStatus.NOT_FOUND);
        return errorMsg;
    }





}
