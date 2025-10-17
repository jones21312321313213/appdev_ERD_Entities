package com.example.appdevf2.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appdevf2.entity.TransactionEntity;
import com.example.appdevf2.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository trepo;

    // C - Create or insert transaction record
    public TransactionEntity insertTransaction(TransactionEntity transaction) {
        return trepo.save(transaction);
    }

    // R - Read all transaction records
    public List<TransactionEntity> getAllTransactions() {
        return trepo.findAll();
    }

    // U - Update a transaction
    @SuppressWarnings("finally")
    public TransactionEntity updateTransaction(int billID, TransactionEntity newTransactionDetails) {
        TransactionEntity t = new TransactionEntity();
        try {
            // 1) Search the transaction by ID
            t = trepo.findById(billID).get();

            // 2) Update the record
            t.setAmount(newTransactionDetails.getAmount());
            t.setCreation_date(newTransactionDetails.getCreation_date());
            t.setDescription(newTransactionDetails.getDescription());

        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Transaction: " + billID + " is not found");
        } finally {
            return trepo.save(t);
        }
    }

    // D - Delete a transaction
    public String deleteTransaction(int billID) {
        String msg = "";
        if (trepo.existsById(billID)) {
            trepo.deleteById(billID);
            msg = "Transaction: " + billID + " is successfully deleted!";
        } else {
            msg = "Transaction: " + billID + " does not exist.";
        }
        return msg;
    }
}
