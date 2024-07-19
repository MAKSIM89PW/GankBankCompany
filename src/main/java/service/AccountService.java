package service;

import model.Account;
import dto.AccountDto;

import java.util.List;

public interface AccountService {
    Account createAccount(AccountDto accountDto);
    Account getAccount(Long accountId);
    List<Account> getAllAccounts();
    void deposit(Long accountId, double amount);
    void withdraw(Long accountId, double amount, int pinCode);
    void transfer(Long fromAccountId, Long toAccountId, double amount, int pinCode);
}

