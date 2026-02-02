package com.example.expense.services;

import com.example.expense.dto.ExpenseRequest;
import com.example.expense.model.ExpenseEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ExpenseService {
    public ResponseEntity<ExpenseEntity> createExpense(
            String idempotencyKey,
            ExpenseRequest request
    );
    List<ExpenseEntity> getExpenses(String category, String sort);
}
