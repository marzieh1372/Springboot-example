package com.example.bank.service;

import com.example.bank.exceptions.AccountNotFoundException;
import com.example.bank.exceptions.AmountRestrictionException;
import com.example.bank.exceptions.CustomerNotFoundException;
import com.example.bank.model.dto.AccountDepositDto;
import com.example.bank.model.entity.Account;
import com.example.bank.model.entity.Customer;
import com.example.bank.repo.AccountRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerTemplateAvailabilityProvider;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger LOGGER = LogManager.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAllAccount() {
        LOGGER.info("Starting getAllAccount method {}");
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountById(Long id) {
        LOGGER.info("Starting getAccountById method {" + id + "}");
        Optional<Account> accountData = accountRepository.findById(id);
        return accountData.orElse(null);
    }

    @Override
    public Account registerAccount(Account account) {
        LOGGER.info("Starting registerAccount method {" + account.getAccountNo() + "}");
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(Long id, Account account) throws AccountNotFoundException {
        LOGGER.info("Starting updateAccount method {" + id +"}");
        Optional<Account> accountData = accountRepository.findById(id);
        if (accountData.isPresent()) {
            Account updatedAccount = accountData.get();
            updatedAccount.setAccountNo(account.getAccountNo());
            return accountRepository.save(updatedAccount);
        }else {
            LOGGER.error("Could no find account!!!!! , Id is wrong : " + id);
            throw new AccountNotFoundException("Could not find account with id: " + id);
        }
    }

    @Override
    public void deleteAccountById(Long id) {
        LOGGER.debug("Starting deleteAccountById method {" + id + "}");
        //TODO find
        accountRepository.deleteById(id);
    }

    @Override
    public BigDecimal getBalance(String accountNo) throws AccountNotFoundException {
        LOGGER.debug("Starting getBalance method { "+accountNo+" }");
        Account account = accountRepository.findByAccountNo(accountNo);
        if (account !=null){
            return account.getBalance();
        }else {
            throw new AccountNotFoundException("Your accountNo is wrong and can not find it!!!");
        }
    }

    @Override
    public BigDecimal AddDeposit(AccountDepositDto accountDepositDto) throws AccountNotFoundException {
        LOGGER.debug("Starting AddDeposit method { "+accountDepositDto.getAccountNo()+" }");
        Account account = accountRepository.findByAccountNo(accountDepositDto.getAccountNo());
        if (account != null){
            account.setBalance(account.getBalance().add(accountDepositDto.getAmount()));
            Account savedAcc = accountRepository.save(account);
            return savedAcc.getBalance();
        }else {
            throw new AccountNotFoundException("Your accountNo is wrong and can not find it!!!");
        }
    }

    @Override
    public BigDecimal withdrawal(AccountDepositDto accountDepositDto) throws AccountNotFoundException {
        LOGGER.debug("Starting AddDeposit method { "+accountDepositDto.getAccountNo()+" }");
        Account account = accountRepository.findByAccountNo(accountDepositDto.getAccountNo());
        if (account != null){
            if (account.getBalance().compareTo(accountDepositDto.getAmount()) < 0){
                throw  new AmountRestrictionException("Balance is not enough!!!");
            }else {
                account.setBalance(account.getBalance().subtract(accountDepositDto.getAmount()));
                return  accountRepository.save(account).getBalance();
            }
        }else {
            throw new AccountNotFoundException("Your accountNo is wrong and can not find it!!!");
        }
    }
}
