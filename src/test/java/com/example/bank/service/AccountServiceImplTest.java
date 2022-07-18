package com.example.bank.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

import com.example.bank.exceptions.AccountNotFoundException;
import com.example.bank.exceptions.AmountRestrictionException;
import com.example.bank.model.dto.AccountDepositDto;

import com.example.bank.model.entity.Account;
import com.example.bank.model.entity.Customer;
import com.example.bank.repo.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;


@ExtendWith(value = MockitoExtension.class)
public class AccountServiceImplTest {

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
    when(accountRepository.findById(12L)).thenThrow(AccountNotFoundException.class);

    assertThrows(AccountNotFoundException.class, () -> accountService.getAccountById(12L));
  }

  @Test
  public void getAccountByIDTest_OK() {
    given(accountRepository.findById(1234L)).willReturn(Optional.ofNullable(getMockAccount()));

    assertEquals("Accoun nomber is correct!", "abcd123", accountService.getAccountById(1234L).getAccountNo());
  }

  @Test
  public void registerAccountTest_OK(){
    Account newAccount = new Account(1234L, "abcd123", new Customer(1234L, "MAAS12", "Maryam",
        "Askari", "abcd@gmail.com", "1234567890"), BigDecimal.valueOf(123.04));

    when(accountRepository.save(newAccount)).thenReturn(getMockAccount());

    assertEquals("Account is registered with Id: ", 1234L,
        accountService.registerAccount(newAccount).getId());

    assertEquals("Account is registered with account number:", "abcd123",
        accountService.registerAccount(newAccount).getAccountNo());
  }

  @Test
  public void updateAccountTest_ThrowNotFoundException() {
    Account updatedAccount = new Account(9876L, "12vbgdf", new Customer(9876000L, "MAAS12", "Maryam",
        "Askari", "abcd@gmail.com", "1234567890"), BigDecimal.valueOf(123.04));

    when(accountRepository.findById(9876L)).thenThrow(AccountNotFoundException.class);

    assertThrows(AccountNotFoundException.class, ()-> accountService.updateAccount(9876L, updatedAccount));
  }

  @Test
  public void updateAccount_Successful() {
    Account updatedAccount = new Account(1234L, "12vbgdf", new Customer(9876000L, "MAAS12", "Maryam",
        "Askari", "abcd@gmail.com", "1234567890"), BigDecimal.valueOf(123.04));

    given(accountRepository.findById(1234L)).willReturn(Optional.of(updatedAccount));
    given(accountRepository.save(updatedAccount)).willReturn(mockAccountToAccount(updatedAccount));

    assertEquals("The account number after updating: ", "12vbgdf",
        accountService.updateAccount(1234L, updatedAccount).getAccountNo());

  }

  // ToDO: write a test for deleteAccountById, after completeing the method.

  @Test
  public void getBalanceTest_ThrowNotFoundException() {
    given(accountRepository.findById(12345L)).willThrow(AccountNotFoundException.class);

    assertThrows(AccountNotFoundException.class, () -> accountService.getBalance(12345L));
  }

  @Test
  public void getBalanceTest_Successful() {
    when(accountRepository.findById(1234L)).thenReturn(Optional.ofNullable(getMockAccount()));

    assertEquals("Balance is: ", BigDecimal.valueOf(123.04), accountService.getBalance(1234L));
  }

  @Test
  public void addDepositTest_throwsAccountNotFoundException() {
    AccountDepositDto accountDeposit = new AccountDepositDto(1234L,"MMMnnnn12", BigDecimal.valueOf(1234.0));

    when(accountRepository.findByAccountNo("MMMnnnn12")).thenThrow(AccountNotFoundException.class);

    assertThrows(AccountNotFoundException.class, ()->accountService.addDeposit(accountDeposit));
  }

  @Test
  public void addDepositTest_successful() {
    AccountDepositDto accountDeposit = new AccountDepositDto(1234L,"abcd123", BigDecimal.valueOf(24.1));

    Account resultAccount = getMockAccount();

    when(accountRepository.findByAccountNo("abcd123")).thenReturn(getMockAccount());
    resultAccount.setBalance(resultAccount.getBalance().add(accountDeposit.getAmmount()));
    when(accountRepository.save(any())).thenReturn(resultAccount);

    assertEquals("new ammount is: ",BigDecimal.valueOf(147.14), accountService.addDeposit(accountDeposit));

  }

  @Test
  public void withdrawalTest_ThrowAccountNotFoundException() {
    AccountDepositDto accountDeposit = new AccountDepositDto(1234L,"MMMnnnn12", BigDecimal.valueOf(1234.0));

    when(accountRepository.findByAccountNo("MMMnnnn12")).thenThrow(AccountNotFoundException.class);

    assertThrows(AccountNotFoundException.class, ()->accountService.withdrawal(accountDeposit));
  }

  @Test
  public void withdrawalTest_ThrowAmountRestrictionException() {
    AccountDepositDto accountDeposit = new AccountDepositDto(1234L,"abcd123", BigDecimal.valueOf(1234.0));

    when(accountRepository.findByAccountNo("abcd123")).thenReturn(getMockAccount());
    assertThrows(AmountRestrictionException.class, () -> accountService.withdrawal(accountDeposit));
  }

  @Test
  public void withdrawalTest_successful() {
    AccountDepositDto accountDeposit = new AccountDepositDto(1234L,"abcd123", BigDecimal.valueOf(12.0));

    Account resultAccount = getMockAccount();
    when(accountRepository.findByAccountNo("abcd123")).thenReturn(getMockAccount());

    resultAccount.setBalance(resultAccount.getBalance().subtract(accountDeposit.getAmmount()));

    when(accountRepository.save(any())).thenReturn(resultAccount);

    assertEquals("New balance after withdrawal is:", BigDecimal.valueOf(111.04), accountService.withdrawal(accountDeposit));
  }

  private Account getMockAccount(){
    return mockAccount(
        1234L,
        "abcd123",
        new Customer(1234L, "MAAS12", "Maryam",
            "Askari", "abcd@gmail.com", "1234567890"),
        BigDecimal.valueOf(123.04));
  }

  private Account mockAccount(Long id, String accountNo, Customer customer, BigDecimal balance){
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
