package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.model.Category;
import com.example.ExpenseTracker.model.Expense;
import com.example.ExpenseTracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService service;

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpense() {
        return ResponseEntity.ok(service.getAllExpense());
    }

    @GetMapping("{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getExpenseById(id));
    }

    @PostMapping
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense) {
        return ResponseEntity.ok(service.addExpense(expense));
    }

    @PutMapping("{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense updatedExpense) {
        updatedExpense.setId(id);
        return ResponseEntity.ok(service.addExpense(updatedExpense));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long id) {
        service.deleteExpense(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/total")
    public ResponseEntity<Double> getSumOfAllExpenses() {
        Double totalSum = service.getSumOfAllExpenses();

        if(totalSum == null) {
            totalSum = 0.0;
        }
        return ResponseEntity.ok(totalSum);
    }

    @GetMapping("/filter/{category}")
    private ResponseEntity<List<Expense>> getExpensesByCategory(@PathVariable Category category) {
        List<Expense> expenses = service.getExpensesByCategory(category);
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/filter/date")
    public ResponseEntity<List<Expense>> getExpensesByRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate endDate
            ) {
        List<Expense> expenses = service.getExpensesByDateRange(startDate, endDate);
        return ResponseEntity.ok(expenses);
    }
}
