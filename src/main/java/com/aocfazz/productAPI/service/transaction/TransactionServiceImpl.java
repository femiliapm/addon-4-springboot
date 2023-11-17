package com.aocfazz.productAPI.service.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aocfazz.productAPI.model.Customer;
import com.aocfazz.productAPI.model.Transaction;
import com.aocfazz.productAPI.payload.req.TransactionReq;
import com.aocfazz.productAPI.payload.res.ResponseHandler;
import com.aocfazz.productAPI.repository.TransactionRepository;
import com.aocfazz.productAPI.service.customer.CustomerService;

@Service
public class TransactionServiceImpl implements TransactionService {
  TransactionRepository transactionRepository;

  @Autowired
  CustomerService customerService;

  public TransactionServiceImpl(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  @Override
  public ResponseEntity<?> createTransactionService(TransactionReq req) {
    // find customer
    Customer customer = customerService.getCustomerService(req.getCustomerId());

    Transaction transaction = new Transaction(req.getStatus(), customer);

    // save ke db
    transactionRepository.save(transaction);

    return ResponseHandler.response(201, "success", transaction, null, true);
  }

  @Override
  public Transaction getTransactionByIdService(String id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getTransactionByIdService'");
  }

}
