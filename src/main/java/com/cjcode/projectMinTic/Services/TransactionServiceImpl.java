package com.cjcode.projectMinTic.Services;

import com.cjcode.projectMinTic.Entities.Enterprise;
import com.cjcode.projectMinTic.Entities.Transaction;
import com.cjcode.projectMinTic.Repositories.EnterpriseRepository;
import com.cjcode.projectMinTic.Repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private EnterpriseRepository enterpriseRepository;
    @Autowired
    private UtilsService utilsService;

    @Override
    public List<Transaction> getAllTransactionsMVC(Long id) {
        List<Transaction> transactions = transactionRepository.findByEnterpriseId(id);
        if(transactions.isEmpty()){
            return  null;
        }
        return transactions;
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).get();
    }

    @Override
    public ResponseEntity<?> getAllTransactions(Long id) {
        List<Transaction> transactions = transactionRepository.findByEnterpriseId(id);
        if(transactions.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empresa no Registra Movimientos");
        }
        return ResponseEntity.ok(transactions);
    }

    @Override
    public ResponseEntity<?> createTransaction(Long id, Transaction transaction) {
        Optional<Enterprise> enterprise = enterpriseRepository.findById(id);
        if(enterprise.isEmpty()){
            return ResponseEntity.badRequest().body("Empresa no registrada");
        }
        transaction.setEnterprise(enterprise.get());
        transaction.setCreateAt(new Date());
        return ResponseEntity.ok(transactionRepository.save(transaction));
    }

    @Override
    public ResponseEntity<?> updateTransaction(Long enterpriseId, Long id, Transaction transaction) {
        Optional<Enterprise> enterprise = enterpriseRepository.findById(enterpriseId);
        if(enterprise.isEmpty()){
            return ResponseEntity.badRequest().body("Empresa no registrada");
        }
        Optional<Transaction> transactionDb = transactionRepository.findById(id);
        if(transactionDb.isEmpty()){
            return ResponseEntity.badRequest().body("Transaccion no registrada");
        }
        Transaction transactionSave = (Transaction) utilsService.validateData(transactionDb.get(),transaction);
        transactionSave.setUpdateAt(new Date());
        return ResponseEntity.ok(transactionRepository.save(transactionSave));
    }

    @Override
    public ResponseEntity<?> deleteTransaction(Long enterpriseId, Long id) {
        Optional<Enterprise> enterprise = enterpriseRepository.findById(enterpriseId);
        if(enterprise.isEmpty()){
            return ResponseEntity.badRequest().body("Empresa no registrada");
        }
        Optional<Transaction> transactionDb = transactionRepository.findById(id);
        if(transactionDb.isEmpty()){
            return ResponseEntity.badRequest().body("Transaccion no registrada");
        }
        transactionRepository.deleteById(id);
        return ResponseEntity.ok("Transaccion Eliminada Correctamente");
    }
}
