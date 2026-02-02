package com.example.expense.controller;

import com.example.expense.dto.ExpenseRequest;
import com.example.expense.model.ExpenseEntity;

import com.example.expense.services.ExpenseServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
@CrossOrigin
public class ExpenseController {

    @Autowired
    private ExpenseServiceImpl service;


    @PostMapping
    public ResponseEntity<ExpenseEntity> createExpense(
            @RequestHeader("Idempotency-Key") String idempotencyKey,
            @Valid @RequestBody ExpenseRequest request
    ) {
        return service.createExpense(idempotencyKey, request);
    }
    @GetMapping
    public List<ExpenseEntity> getExpenses(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String sort
    ) {
        return service.getExpenses(category, sort);
    }
}
