package service;

import model.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getTransactionsByAccountId(Long accountId);
}
