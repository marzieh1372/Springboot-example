package com.example.bank.service;

import com.example.bank.config.AccountProperty;
import com.example.bank.exceptions.AccountNotFoundException;
import com.example.bank.exceptions.AmountRestrictionException;
import com.example.bank.mapper.AccountMapper;
import com.example.bank.model.dto.AccountRequest;
import com.example.bank.model.dto.Deposit;
import com.example.bank.model.dto.Withdrawal;
import com.example.bank.model.entity.Account;
import com.example.bank.repo.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Qualifier("accountServiceImpl")
public class AccountServiceImpl implements AccountService {

  @Autowired private AccountRepository accountRepository;

  private AccountMapper mapper;

  /*
     You can use @Value ro read properties from application file
     or You can create property class like accountProperty
  */

  /* @Value("${account.charity}")
  private Double charityValue;*/

  @Autowired private AccountProperty accountProperty;

  @Override
  public List<Account> getAllAccount() {
    log.info("Starting getAllAccount method {}");
    List<Account> accounts = accountRepository.findAll();
    for (Account a : accounts) {
      // if (a.getBalance() < charityValue){
      if (a.getBalance() < accountProperty.getCharity()) {
        a.setBalance(200.0);
      }
    }
    return accounts;
  }

  @Override
  public AccountRequest getAccountById(Integer id) {
    log.info("Getting account by id{}", id);
    Optional<Account> account = accountRepository.findById(id);
    if (account.isPresent()) {
      AccountRequest accountRequest = mapper.accountMapToAccountRequest(account.get());
      return accountRequest;
    } else {
      log.warn("Account not found with id{}", id);
      throw new AccountNotFoundException("Account not fount with id: " + id);
    }
  }

  @Override
  public void registerAccount(AccountRequest accountRequest) {
    log.info("Starting registerAccount method");
    Account account = mapper.accountRequestMapToAccount(accountRequest);
    accountRepository.save(account);
  }

  @Override
  public void updateAccount(Integer accountId, AccountRequest accountRequest)
      throws AccountNotFoundException {
    log.info("Updating account by accountId {}", accountId);
    Optional<Account> accountData = accountRepository.findById(accountId);
    if (accountData.isPresent()) {
      Account accountInfo = accountData.get();
      if (accountInfo.getCustomer().getId().equals(accountRequest.getCustomerId())) {
        accountInfo = mapper.accountRequestMapToAccount(accountRequest);
        accountRepository.save(accountInfo);
      } else {
        log.warn(
            "Can not find customerId{} by accountId{}", accountRequest.getCustomerId(), accountId);
      }
    } else {
      log.warn("Account can not find by id{}", accountId);
      throw new AccountNotFoundException("Cannot fid account with id: " + accountId);
    }
  }

  @Override
  public void deleteAccountById(Integer id) {
    log.info("Deleting account by id{}", id);
    Optional<Account> accountData = accountRepository.findById(id);
    if (accountData.isPresent()) {
      accountRepository.deleteById(id);
    } else {
      log.warn("Account can not find by id{}", id);
      throw new AccountNotFoundException("Cannot fid account with id: " + id);
    }
  }

  @Override
  public Double getBalance(Integer accountId) throws AccountNotFoundException {
    log.debug("Starting getBalance method {" + accountId + "}");
    Optional<Account> accountData = accountRepository.findById(accountId);
    if (accountData != null) {
      Account account = accountData.get();
      return account.getBalance();
    } else {
      throw new AccountNotFoundException("Your account Number is wrong!");
    }
  }

  @Override
  public Double addDeposit(Integer accountId, Deposit deposit) throws AccountNotFoundException {
    log.debug("Starting addDeposit method {" + accountId + "}");
    Optional<Account> accountData = accountRepository.findById(accountId);
    if (accountData != null) {
      Account account = accountData.get();
      account.setBalance(account.getBalance() + (deposit.getAmount()));
      Account savedAccount = accountRepository.save(account);
      return savedAccount.getBalance();
    } else {
      throw new AccountNotFoundException("Your account number is wrong!");
    }
  }

  @Override
  public Double withdrawal(Integer accountId, Withdrawal withdrawal)
      throws AccountNotFoundException {
    log.debug("Starting withdrawal Method {" + accountId + "}");
    Optional<Account> accountData = accountRepository.findById(accountId);
    if (accountData != null) {
      Account account = accountData.get();
      if (account.getBalance().compareTo(withdrawal.getAmount()) < 0) {
        throw new AmountRestrictionException("Amount is high! Balance is not enough!");
      } else {
        account.setBalance(account.getBalance() - (withdrawal.getAmount()));
        return accountRepository.save(account).getBalance();
      }
    } else {
      throw new AccountNotFoundException("Your Account Number is wrong!");
    }
  }
}
