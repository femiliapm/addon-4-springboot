package com.aocfazz.productAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aocfazz.productAPI.model.Order;

public interface OrderRepository extends JpaRepository<Order, String> {

}
