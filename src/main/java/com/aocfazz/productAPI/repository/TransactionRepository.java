package com.aocfazz.productAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aocfazz.productAPI.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

}
