package com.aocfazz.productAPI.service.order;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.aocfazz.productAPI.model.Order;
import com.aocfazz.productAPI.model.Product;
import com.aocfazz.productAPI.payload.req.OrderReq;
import com.aocfazz.productAPI.payload.res.ResponseHandler;
import com.aocfazz.productAPI.repository.OrderRepository;
import com.aocfazz.productAPI.repository.ProductRepository;
import com.aocfazz.productAPI.service.product.ProductService;

@Service
public class OrderServiceImpl implements OrderService {
  ProductRepository productRepository;
  OrderRepository orderRepository;

  @Autowired
  ProductService productService;

  public OrderServiceImpl(ProductRepository productRepository, OrderRepository orderRepository) {
    this.productRepository = productRepository;
    this.orderRepository = orderRepository;
  }

  @Override
  public ResponseEntity<?> createOrderService(OrderReq req) {
    // find product
    // Product product = productRepository.findById(req.getProductId())
    // .orElseThrow(() -> new NoSuchElementException("Id Product is not found!"));

    Product product = productService.getProductByIdService(req.getProductId());

    if (product.getIsDeleted()) {
      throw new NoSuchElementException("Product is already deleted!");
    }

    Double tPrice = product.getPrice() * req.getKuantitas();
    Order order = new Order(product, req.getKuantitas(), tPrice);

    // save ke db
    orderRepository.save(order);

    return ResponseHandler.response(201, "successfully created order!", order, null, true);
  }

  @Override
  public ResponseEntity<?> getAllOrderService() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAllOrderService'");
  }

  @Override
  public ResponseEntity<?> getByIdService(String idOrder) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getByIdService'");
  }

}
