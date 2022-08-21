package com.example.bank.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.example.bank.model.dto.AccountRequest;
import com.example.bank.model.entity.Account;
import com.example.bank.model.entity.Customer;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

public class AccountMapperTest {
  private final AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);

  @Test
  public void accountRequestMapToAccountTest() {
    AccountRequest request = AccountRequest.builder().customerId(123).balance(20.00).build();
    Account mapper = accountMapper.accountRequestMapToAccount(request);

    assertEquals(request.getBalance(), mapper.getBalance());
    assertEquals(request.getCustomerId(), mapper.getCustomer().getId());
  }

  @Test
  public void accountMapToAccountRequestTest() {
    Account model =
        Account.builder().balance(200.00).customer(Customer.builder().id(12345).build()).build();
    AccountRequest request = accountMapper.accountMapToAccountRequest(model);

    assertEquals(model.getBalance(), request.getBalance());
    assertEquals(model.getCustomer().getId(), request.getCustomerId());
  }
}
