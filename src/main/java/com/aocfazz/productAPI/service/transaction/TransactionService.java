package com.aocfazz.productAPI.service.transaction;

import org.springframework.http.ResponseEntity;

import com.aocfazz.productAPI.model.Transaction;
import com.aocfazz.productAPI.payload.req.TransactionReq;

public interface TransactionService {
  ResponseEntity<?> createTransactionService(TransactionReq req);

  Transaction getTransactionByIdService(String id);
}
