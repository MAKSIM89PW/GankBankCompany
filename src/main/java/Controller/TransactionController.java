package Controller;

import model.Transaction;
import service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/account/{accountId}")
    public List<Transaction> getTransactionsForAccount(@PathVariable Long accountId) {
        return transactionService.getTransactionsForAccount(accountId);
    }
}
