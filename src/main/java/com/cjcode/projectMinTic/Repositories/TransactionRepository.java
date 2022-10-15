package com.cjcode.projectMinTic.Repositories;

import com.cjcode.projectMinTic.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByEnterpriseId(Long id);
}
