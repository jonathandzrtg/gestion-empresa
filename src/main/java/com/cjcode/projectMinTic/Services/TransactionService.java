package com.cjcode.projectMinTic.Services;

import com.cjcode.projectMinTic.Entities.Transaction;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TransactionService {
    List<Transaction> getAllTransactionsMVC(Long id);
    Transaction getTransactionById(Long id);
    ResponseEntity<?> getAllTransactions(Long id);
    ResponseEntity<?> createTransaction(Long id, Transaction transaction);
    ResponseEntity<?> updateTransaction(Long enterpriseId, Long id, Transaction transaction);
    ResponseEntity<?> deleteTransaction(Long enterpriseId, Long id);

}
