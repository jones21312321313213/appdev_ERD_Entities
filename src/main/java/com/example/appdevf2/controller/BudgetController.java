package com.example.appdevf2.controller;

import com.example.appdevf2.entity.BudgetEntity;
import com.example.appdevf2.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    // POST: Create a new Budget
    @PostMapping
    public ResponseEntity<BudgetEntity> createBudget(@RequestBody BudgetEntity budget) {
        BudgetEntity savedBudget = budgetService.saveBudget(budget);
        return new ResponseEntity<>(savedBudget, HttpStatus.CREATED);
    }

    // GET: Get all Budgets
    @GetMapping
    public ResponseEntity<List<BudgetEntity>> getAllBudgets() {
        List<BudgetEntity> budgets = budgetService.getAllBudgets();
        return new ResponseEntity<>(budgets, HttpStatus.OK);
    }

    // GET: Get Budget by ID
    @GetMapping("/{id}")
    public ResponseEntity<BudgetEntity> getBudgetById(@PathVariable int id) {
        return budgetService.getBudgetById(id)
                .map(budget -> new ResponseEntity<>(budget, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // PUT: Update an existing Budget
    @PutMapping("/{id}")
    public ResponseEntity<BudgetEntity> updateBudget(@PathVariable int id, @RequestBody BudgetEntity budgetDetails) {
        return budgetService.getBudgetById(id)
                .map(existingBudget -> {
                    // Update fields
                    existingBudget.setCategory_name(budgetDetails.getCategory_name());
                    existingBudget.setTotal_amount(budgetDetails.getTotal_amount());
                    existingBudget.setPeriod(budgetDetails.getPeriod());
                    
                    BudgetEntity updatedBudget = budgetService.saveBudget(existingBudget);
                    return new ResponseEntity<>(updatedBudget, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // DELETE: Delete Budget by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable int id) {
        if (budgetService.getBudgetById(id).isPresent()) {
            budgetService.deleteBudget(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}