package com.aocfazz.productAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aocfazz.productAPI.payload.req.CustomerReq;
import com.aocfazz.productAPI.payload.req.LoginReq;
import com.aocfazz.productAPI.service.customer.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
  @Autowired
  CustomerService customerService;

  @PostMapping
  public ResponseEntity<?> createCustomer(@RequestBody CustomerReq req) {
    return customerService.registCustomerService(req);
  }

  @PostMapping("/login")
  public ResponseEntity<?> loginCustomer(@RequestBody LoginReq req) {
    return customerService.loginCustomer(req);
  }
}
