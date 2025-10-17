package com.example.appdevf2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.appdevf2.entity.TransactionEntity;
import com.example.appdevf2.service.TransactionService;

@RestController
@RequestMapping("api/transaction")
public class TransactionController {

    @Autowired
    private TransactionService tserv;

    // C - create a transaction
    @PostMapping("/insertTransaction")
    public TransactionEntity insertTransaction(@RequestBody TransactionEntity transaction) {
        return tserv.insertTransaction(transaction);
    }

    // R - read all transactions
    @GetMapping("/getAllTransactions")
    public List<TransactionEntity> getAllTransactions() {
        return tserv.getAllTransactions();
    }

    // U - update a transaction
    @PutMapping("/updateTransaction")
    public TransactionEntity updateTransaction(@RequestParam int tid, @RequestBody TransactionEntity newTransactionDetails) {
        return tserv.updateTransaction(tid, newTransactionDetails);
    }

    // D - delete a transaction
    @DeleteMapping("/deleteTransaction/{tid}")
    public String deleteTransaction(@PathVariable int tid) {
        return tserv.deleteTransaction(tid);
    }
}
