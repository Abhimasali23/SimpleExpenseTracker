package com.example.ExpenseTracker.service;

import com.example.ExpenseTracker.exception.ResourceNotFoundException;
import com.example.ExpenseTracker.model.Category;
import com.example.ExpenseTracker.model.Expense;
import com.example.ExpenseTracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository repo;

    public List<Expense> getAllExpense() {
        return repo.findAll();
    }

    public Expense getExpenseById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + id));
    }

    public Expense addExpense(Expense expense) {
        return repo.save(expense);
    }

    public void deleteExpense(Long id) {
        repo.deleteById(id);
    }

    public Double getSumOfAllExpenses() {
        return repo.sumOfTotalExpenses();
    }

    public List<Expense> getExpensesByCategory(Category category) {
        return repo.findByCategory(category);
    }

    public List<Expense> getExpensesByDateRange(LocalDate start, LocalDate end) {
        if(start.isAfter(end)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }

        return  repo.findByDateBetween(start, end);
    }
}
