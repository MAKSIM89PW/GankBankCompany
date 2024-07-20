package Bank.Controller;

import Bank.dto.TransactionDto;
import Bank.model.Account;
import Bank.service.AccountService;
import Bank.dto.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
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
    public void deposit(@PathVariable Long accountId, @RequestBody double amount) {
        accountService.deposit(accountId, amount);
    }

    @PostMapping("/{accountId}/withdraw")
    public void withdraw(@PathVariable Long accountId, @RequestBody TransactionDto transactionDto) {
        accountService.withdraw(accountId, transactionDto.getAmount(), transactionDto.getPinCode());
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
