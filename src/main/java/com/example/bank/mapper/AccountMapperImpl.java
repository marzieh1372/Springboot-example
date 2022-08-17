/*
package com.example.bank.mapper;

import com.example.bank.model.dto.AccountRequest;
import com.example.bank.model.entity.Account;

public class AccountMapperImpl implements AccountMapper{
  @Override
  public Account accountRequestMapToAccount(AccountRequest accountRequest) {
    if(accountRequest == null){
      return null;
    }
    Account account = new Account();
    account.setBalance(accountRequest.getBalance());
    account.getCustomer().setId(accountRequest.getCustomerId());
    return account;
  }

  @Override
  public AccountRequest accountMapToAccountRequest(Account account) {
    if (account == null) {
      return null;
    }
    AccountRequest accountRequest = new AccountRequest();
    accountRequest.setBalance(account.getBalance());
    accountRequest.setCustomerId(account.getCustomer().getId());
    return accountRequest;
  }
}
*/
