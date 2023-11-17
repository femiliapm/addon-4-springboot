package com.aocfazz.productAPI.service.customer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aocfazz.productAPI.model.Customer;
import com.aocfazz.productAPI.payload.req.CustomerReq;
import com.aocfazz.productAPI.payload.req.LoginReq;
import com.aocfazz.productAPI.payload.res.ResponseHandler;
import com.aocfazz.productAPI.repository.CustomerRepository;
import com.aocfazz.productAPI.util.JwtUtil;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CustomerServiceImpl implements CustomerService {
  CustomerRepository customerRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  JwtUtil jwtUtil;

  @Autowired
  AuthenticationManager authenticationManager;

  public CustomerServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public ResponseEntity<?> registCustomerService(CustomerReq req) {
    // check unique value
    if (customerRepository.existsByEmail(req.getEmail())) {
      throw new EntityExistsException("Email is already registered!");
    }

    if (customerRepository.existsByUsername(req.getUsername())) {
      throw new EntityExistsException("Username is already registered!");
    }

    // instance obj customer
    Customer customer = new Customer(req.getFname(), req.getLname(), req.getEmail(), req.getUsername(),
        passwordEncoder.encode(req.getPassword()));

    // save ke db
    customerRepository.save(customer);

    return ResponseHandler.response(201, "successfully created customer!", customer, null, true);
  }

  @Override
  public ResponseEntity<?> loginCustomer(LoginReq req) {
    // check email sudah terdaftar / belum
    if (!customerRepository.existsByEmail(req.getEmail())) {
      throw new EntityNotFoundException("Email is not found!");
    }

    Customer customer = customerRepository.findByEmail(req.getEmail());

    // password match atau tidak
    if (!passwordEncoder.matches(req.getPassword(), customer.getPassword())) {
      throw new RuntimeException("Bad credentials! Password doesn't match!");
    }

    // auth token / start login prosess
    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(req.getEmail(),
        req.getPassword());

    // autentikasi usernya
    Authentication authentication = authenticationManager.authenticate(authenticationToken);

    // buatkan context holder
    SecurityContextHolder.getContext().setAuthentication(authentication);

    // create token
    String token = jwtUtil.createToken(req.getEmail());

    // response bodynya
    Map<String, Object> data = new HashMap<>();
    data.put("email", req.getEmail());
    data.put("token", token);

    return ResponseHandler.response(200, "success login!", data, null, true);
  }

  public ResponseEntity<?> loginNoHashCustomer(LoginReq req) {
    // check email sudah terdaftar / belum
    if (!customerRepository.existsByEmail(req.getEmail())) {
      throw new EntityNotFoundException("Email is not found!");
    }

    Customer customer = customerRepository.findByEmail(req.getEmail());

    // password match atau tidak
    if (!customer.getPassword().equals(req.getPassword())) {
      throw new RuntimeException("Bad credentials! Password doesn't match!");
    }

    // create token

    return ResponseHandler.response(200, "success login!", customer, null, true);
  }

  @Override
  public Customer getCustomerService(String id) {
    Customer customer = customerRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Id user is not found!"));

    return customer;
  }

}
