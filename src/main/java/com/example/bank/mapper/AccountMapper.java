package com.example.bank.mapper;

import com.example.bank.model.dto.AccountRequest;
import com.example.bank.model.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
  Account accountRequestMapToAccount(AccountRequest accountRequest);
  AccountRequest accountMapToAccountRequest(Account account);
}
