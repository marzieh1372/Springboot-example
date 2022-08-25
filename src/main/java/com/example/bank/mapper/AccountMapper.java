package com.example.bank.mapper;

import com.example.bank.model.dto.AccountRequest;
import com.example.bank.model.entity.Account;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class AccountMapper {

    @Mapping(source = "customerId", target = "customer.id")
    public abstract Account accountRequestMapToAccount(AccountRequest accountRequest);

    @InheritInverseConfiguration
    public abstract AccountRequest accountMapToAccountRequest(Account account);


}
