package com.aocfazz.productAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aocfazz.productAPI.payload.req.OrderReq;
import com.aocfazz.productAPI.service.order.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
  @Autowired
  OrderService orderService;

  @PostMapping
  public ResponseEntity<?> createOrder(@RequestBody OrderReq req) {
    return orderService.createOrderService(req);
  }
}
