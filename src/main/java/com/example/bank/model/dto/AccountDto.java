package com.example.bank.model.dto;

import com.example.bank.model.entity.Customer;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDto {

    Long id;
    String accountNo;
    BigDecimal valance;
    Customer customer;
}
