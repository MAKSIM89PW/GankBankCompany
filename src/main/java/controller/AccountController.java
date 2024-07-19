package controller;

import dto.TransactionDto;
import model.Account;
import dto.AccountDto;
import service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public Account createAccount(@RequestBody AccountDto accountDto) {
        return accountService.createAccount(accountDto);
    }

    @GetMapping("/{accountId}")
    public Account getAccount(@PathVariable Long accountId) {
        return accountService.getAccount(accountId);
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @PostMapping("/{accountId}/deposit")
    public void deposit(@PathVariable Long accountId, @RequestParam double amount) {
        accountService.deposit(accountId, amount);
    }

    @PostMapping("/{accountId}/withdraw")
    public void withdraw(@PathVariable Long accountId, @RequestParam double amount, @RequestParam int pinCode) {
        accountService.withdraw(accountId, amount, pinCode);
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody TransactionDto transactionDto) {
        accountService.transfer(
                transactionDto.getFromAccountId(),
                transactionDto.getToAccountId(),
                transactionDto.getAmount(),
                transactionDto.getPinCode()
        );
    }
}

