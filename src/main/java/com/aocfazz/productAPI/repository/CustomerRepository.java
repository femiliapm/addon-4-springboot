package com.aocfazz.productAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aocfazz.productAPI.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
  // query method
  Boolean existsByEmail(String email);

  Boolean existsByUsername(String username);

  Customer findByEmail(String email);
}
