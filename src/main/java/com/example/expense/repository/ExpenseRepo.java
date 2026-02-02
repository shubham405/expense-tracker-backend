package com.example.expense.repository;

import com.example.expense.model.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExpenseRepo extends JpaRepository<ExpenseEntity, UUID> {

    Optional<ExpenseEntity> findByIdempotencyKey(String idempotencyKey);

    List<ExpenseEntity> findByCategoryIgnoreCase(String category);

    List<ExpenseEntity> findByCategoryIgnoreCaseOrderByDateDesc(String category);

    List<ExpenseEntity> findAllByOrderByDateDesc();

}
