package com.aocfazz.productAPI.service.customer;

import org.springframework.http.ResponseEntity;

import com.aocfazz.productAPI.model.Customer;
import com.aocfazz.productAPI.payload.req.CustomerReq;
import com.aocfazz.productAPI.payload.req.LoginReq;

public interface CustomerService {
  ResponseEntity<?> registCustomerService(CustomerReq req);

  ResponseEntity<?> loginCustomer(LoginReq req);

  Customer getCustomerService(String id);
}
