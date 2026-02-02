package com.example.expense.services;

import com.example.expense.dto.ExpenseRequest;
import com.example.expense.model.ExpenseEntity;
import com.example.expense.repository.ExpenseRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@Slf4j
public class ExpenseServiceImpl implements ExpenseService{

    @Autowired
    private  ExpenseRepo repository;
    @Override
    public ResponseEntity<ExpenseEntity> createExpense(String idempotencyKey, ExpenseRequest request) {

        // 1️⃣ Retry safety
        var existing = repository.findByIdempotencyKey(idempotencyKey);
        if (existing.isPresent()) {
            log.info("duplicate request found returning existing request");
            return ResponseEntity.ok(existing.get());
        }

        log.info("creating a new entry in db");
        ExpenseEntity expense = ExpenseEntity.builder().idempotencyKey(idempotencyKey)
        .amount(request.getAmount())
        .category(request.getCategory().trim())
        .description(request.getDescription())
        .date(request.getDate()).createdAt(Instant.now()).build();
        repository.save(expense);
        return ResponseEntity.status(HttpStatus.CREATED).body(expense);
    }
    @Override
    public List<ExpenseEntity> getExpenses(String category, String sort) {
        if(category!=null)
            category = category.trim();
        boolean sortByDateDesc = "date_desc".equals(sort);

        if (category != null && sortByDateDesc) {
            return repository.findByCategoryIgnoreCaseOrderByDateDesc(category);
        }

        if (sortByDateDesc) {
            return repository.findAllByOrderByDateDesc();
        }

        if (category != null) {
            return repository.findByCategoryIgnoreCase(category);
        }

        return repository.findAll();
    }
}
