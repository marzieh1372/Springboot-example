package com.example.bank.service;

import com.example.bank.config.AccountProperty;
import com.example.bank.exceptions.AccountNotFoundException;
import com.example.bank.exceptions.AmountRestrictionException;
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

    @Autowired
    private AccountRepository accountRepository;

    /*
        You can use @Value ro read properties from application file
        or You can create property class like accountProperty
     */

   /* @Value("${account.charity}")
    private Double charityValue;*/

    @Autowired
    private AccountProperty accountProperty;

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
    public Account getAccountById(Integer id) {
        log.info("Starting getAccountById method {" + id + "}");
        Optional<Account> accountData = accountRepository.findById(id);
        return accountData.orElse(null);
    }

    @Override
    public Account registerAccount(Account account) {
        log.info("Starting registerAccount method {" + account.getAccountNo() + "}");
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(Integer id, Account account) throws AccountNotFoundException {
        log.info("Starting updateAccount method {" + id + "}");
        Optional<Account> accountData = accountRepository.findById(id);
        if (accountData.isPresent()) {
            Account updatedAccount = accountData.get();
            updatedAccount.setAccountNo(account.getAccountNo());
            return accountRepository.save(updatedAccount);
        } else {
            log.error("Could not find account!!!!! , Id is wrong : " + id);
            throw new AccountNotFoundException("Could not find account with id: " + id);
        }
    }

    @Override
    public void deleteAccountById(Integer id) {
        log.debug("Starting deleteAccountById method {" + id + "}");
        //TODO find
        accountRepository.deleteById(id);
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
    public Double withdrawal(Integer accountId, Withdrawal withdrawal) throws AccountNotFoundException {
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
