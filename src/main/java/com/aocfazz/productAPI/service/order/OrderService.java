package com.aocfazz.productAPI.service.order;

import org.springframework.http.ResponseEntity;

import com.aocfazz.productAPI.payload.req.OrderReq;

public interface OrderService {
  ResponseEntity<?> createOrderService(OrderReq req);

  ResponseEntity<?> getAllOrderService();

  ResponseEntity<?> getByIdService(String idOrder);
}
