package com.example.bank.mapper;

import com.example.bank.model.dto.AccountRequest;
import com.example.bank.model.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class AccountMapper {

    public abstract Account accountRequestMapToAccount(AccountRequest accountRequest);

    public abstract AccountRequest accountMapToAccountRequest(Account account);


}
