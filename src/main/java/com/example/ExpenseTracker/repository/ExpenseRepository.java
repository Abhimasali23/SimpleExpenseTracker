package com.example.ExpenseTracker.repository;

import com.example.ExpenseTracker.model.Category;
import com.example.ExpenseTracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("SELECT SUM(e.amount) FROM Expense e")
    Double sumOfTotalExpenses();

    List<Expense> findByCategory(Category category);

    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
