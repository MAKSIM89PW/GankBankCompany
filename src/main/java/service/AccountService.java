package service;

import dto.AccountDto;
import model.Account;
import model.Beneficiary;
import repository.AccountRepository;
import repository.BeneficiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    public Account createAccount(AccountDto accountDto) {
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setName(accountDto.getBeneficiaryName());
        beneficiary = beneficiaryRepository.save(beneficiary);

        Account account = new Account();
        account.setAccountNumber(generateAccountNumber());
        account.setBeneficiary(beneficiary);
        account.setPinCode(accountDto.getPinCode());
        account.setBalance(BigDecimal.ZERO);
        return accountRepository.save(account);
    }

    public Account getAccount(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public void deposit(Long accountId, double amount) {
        Account account = getAccount(accountId);
        account.setBalance(account.getBalance().add(BigDecimal.valueOf(amount)));
        accountRepository.save(account);
    }

    public void withdraw(Long accountId, double amount, int pinCode) {
        Account account = getAccount(accountId);
        if (account.getPinCode() != pinCode) {
            throw new RuntimeException("Invalid PIN code");
        }
        if (account.getBalance().compareTo(BigDecimal.valueOf(amount)) < 0) {
            throw new RuntimeException("Insufficient funds");
        }
        account.setBalance(account.getBalance().subtract(BigDecimal.valueOf(amount)));
        accountRepository.save(account);
    }

    public void transfer(Long fromAccountId, Long toAccountId, double amount, int pinCode) {
        Account fromAccount = getAccount(fromAccountId);
        Account toAccount = getAccount(toAccountId);

        if (fromAccount.getPinCode() != pinCode) {
            throw new RuntimeException("Invalid PIN code");
        }
        if (fromAccount.getBalance().compareTo(BigDecimal.valueOf(amount)) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(BigDecimal.valueOf(amount)));
        toAccount.setBalance(toAccount.getBalance().add(BigDecimal.valueOf(amount)));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }

    private String generateAccountNumber() {
        // Simple account number generation logic. You can replace this with a more robust logic.
        return String.valueOf(System.currentTimeMillis());
    }
}
