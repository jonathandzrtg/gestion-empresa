package com.cjcode.projectMinTic.Controllers;

import com.cjcode.projectMinTic.Entities.Employee;
import com.cjcode.projectMinTic.Entities.Transaction;
import com.cjcode.projectMinTic.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/enterprises")
public class TransactionController {
    @Autowired
    private TransactionService service;

    @GetMapping("/{enterpriseId}/movements")
    public ResponseEntity<?> getAllTransactions(@PathVariable("enterpriseId") Long enterpriseId){
        return  service.getAllTransactions(enterpriseId);
    }

    @PostMapping("/{enterpriseId}/movements")
    public ResponseEntity<?> createTransaction(@PathVariable("enterpriseId") Long enterpriseId,
                                               @RequestBody Transaction transaction){
        return service.createTransaction(enterpriseId,transaction);
    }

    @PatchMapping("/{enterpriseId}/movements/{id}")
    public ResponseEntity<?> updateTransaction(@PathVariable("enterpriseId") Long enterpriseId,
                                               @PathVariable("id") Long id,
                                               @RequestBody Transaction transaction){
        return service.updateTransaction(enterpriseId,id,transaction);
    }

    @DeleteMapping("/{enterpriseId}/movements/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable("enterpriseId") Long enterpriseId,
                                               @PathVariable("id") Long id){
        return service.deleteTransaction(enterpriseId,id);
    }
}
