package com.example.bank.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

import com.example.bank.exceptions.AccountNotFoundException;
import com.example.bank.exceptions.AmountRestrictionException;

import com.example.bank.model.dto.Deposit;
import com.example.bank.model.dto.Withdrawal;
import com.example.bank.model.entity.Account;
import com.example.bank.model.entity.Customer;
import com.example.bank.repo.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;


@ExtendWith(value = MockitoExtension.class)
public class AccountServiceImplTest {
  private static final Integer ACCOUNT_ID = 1234;

  @Mock
  AccountRepository accountRepository;

  @InjectMocks
  AccountServiceImpl accountService;

  @Test
  public void getAllAccounts(){
    when(accountRepository.findAll())
        .thenReturn(
            Arrays.asList(getMockAccount()));

    assertEquals(" pass shod!!!!!! ", 1, accountService.getAllAccount().size());
  }

  @Test
  public void getAccountByIdTest_When_IdNotFound() {
    when(accountRepository.findById(12)).thenThrow(AccountNotFoundException.class);

    assertThrows(AccountNotFoundException.class, () -> accountService.getAccountById(12));
  }

  @Test
  public void getAccountByIDTest_OK() {
    given(accountRepository.findById(1234)).willReturn(Optional.ofNullable(getMockAccount()));

    assertEquals("Accoun nomber is correct!", "abcd123", accountService.getAccountById(1234).getAccountNo());
  }

  @Test
  public void registerAccountTest_OK(){
    Account newAccount = new Account(1234, "abcd123", new Customer(1234, "MAAS12", "Maryam",
        "Askari", "abcd@gmail.com", "1234567890"), 10000.0);

    when(accountRepository.save(newAccount)).thenReturn(getMockAccount());

    assertEquals("Account is registered with Id: ", 1234,
        accountService.registerAccount(newAccount).getId());

    assertEquals("Account is registered with account number:", "abcd123",
        accountService.registerAccount(newAccount).getAccountNo());
  }

  @Test
  public void updateAccountTest_ThrowNotFoundException() {
    Account updatedAccount = new Account(9876, "12vbgdf", new Customer(9876000, "MAAS12", "Maryam",
        "Askari", "abcd@gmail.com", "1234567890"), 10000.0);

    when(accountRepository.findById(9876)).thenThrow(AccountNotFoundException.class);

    assertThrows(AccountNotFoundException.class, ()-> accountService.updateAccount(9876, updatedAccount));
  }

  @Test
  public void updateAccount_Successful() {
    Account updatedAccount = new Account(1234, "12vbgdf", new Customer(9876000, "MAAS12", "Maryam",
        "Askari", "abcd@gmail.com", "1234567890"), 10000.0);

    given(accountRepository.findById(1234)).willReturn(Optional.of(updatedAccount));
    given(accountRepository.save(updatedAccount)).willReturn(mockAccountToAccount(updatedAccount));

    assertEquals("The account number after updating: ", "12vbgdf",
        accountService.updateAccount(1234, updatedAccount).getAccountNo());

  }

  // ToDO: write a test for deleteAccountById, after completeing the method.

  @Test
  public void getBalanceTest_ThrowNotFoundException() {
    given(accountRepository.findById(12345)).willThrow(AccountNotFoundException.class);

    assertThrows(AccountNotFoundException.class, () -> accountService.getBalance(12345));
  }

  @Test
  public void getBalanceTest_Successful() {
    when(accountRepository.findById(1234)).thenReturn(Optional.ofNullable(getMockAccount()));

    assertEquals("Balance is: ", 20000.0, accountService.getBalance(1234));
  }

  @Test
  public void addDepositTest_throwsAccountNotFoundException() {
    Deposit deposit = Deposit.builder().amount(10000.0).build();

    when(accountRepository.findById(any())).thenThrow(AccountNotFoundException.class);

    assertThrows(AccountNotFoundException.class, ()->accountService.addDeposit(ACCOUNT_ID, deposit));
  }

  @Test
  public void addDepositTest_successful() {
    Deposit deposit = Deposit.builder().amount(10000.0).build();

    Account resultAccount = getMockAccount();

    when(accountRepository.findById(ACCOUNT_ID)).thenReturn(Optional.ofNullable(getMockAccount()));
    resultAccount.setBalance(resultAccount.getBalance() + (deposit.getAmount()));
    when(accountRepository.save(any())).thenReturn(resultAccount);

    assertEquals("new ammount is: ",30000.0, accountService.addDeposit(ACCOUNT_ID, deposit));

  }

  @Test
  public void withdrawalTest_ThrowAccountNotFoundException() {
    Withdrawal withdrawal = Withdrawal.builder().amount(10000.0).build();

    when(accountRepository.findById(ACCOUNT_ID)).thenThrow(AccountNotFoundException.class);

    assertThrows(AccountNotFoundException.class, ()->accountService.withdrawal(ACCOUNT_ID,withdrawal));
  }

  @Test
  public void withdrawalTest_ThrowAmountRestrictionException() {
    Withdrawal withdrawal = Withdrawal.builder().amount(100000.0).build();

    when(accountRepository.findById(ACCOUNT_ID)).thenReturn(Optional.ofNullable(getMockAccount()));
    assertThrows(AmountRestrictionException.class, () -> accountService.withdrawal(ACCOUNT_ID, withdrawal));
  }

  @Test
  public void withdrawalTest_successful() {
    Withdrawal withdrawal = Withdrawal.builder().amount(10000.0).build();

    Account resultAccount = getMockAccount();
    when(accountRepository.findById(ACCOUNT_ID)).thenReturn(Optional.ofNullable(getMockAccount()));

    resultAccount.setBalance(resultAccount.getBalance() - (withdrawal.getAmount()));

    when(accountRepository.save(any())).thenReturn(resultAccount);

    assertEquals("New balance after withdrawal is:", 10000.0, accountService.withdrawal(ACCOUNT_ID, withdrawal));
  }

  private Account getMockAccount(){
    return mockAccount(
        ACCOUNT_ID,
        "abcd123",
        new Customer(1234, "MAAS12", "Maryam",
            "Askari", "abcd@gmail.com", "1234567890"),
        20000.0);
  }

  private Account mockAccount(Integer id, String accountNo, Customer customer, Double balance){
    return Account.builder()
        .id(id)
        .accountNo(accountNo)
        .customer(customer)
        .balance(balance)
        .build();
  }

  private Account mockAccountToAccount(Account account) {
    return Account.builder()
        .id(account.getId())
        .accountNo(account.getAccountNo())
        .customer(account.getCustomer())
        .balance(account.getBalance())
        .build();
  }

}
