package com.aocfazz.productAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aocfazz.productAPI.payload.req.ProductReq;
import com.aocfazz.productAPI.payload.res.ResponseHandler;
import com.aocfazz.productAPI.service.product.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
  @Autowired
  ProductService productService;

  @PostMapping
  public ResponseEntity<?> addProduct(@RequestBody ProductReq request) {
    try {
      return productService.addProductService(request);
    } catch (Exception e) {
      return ResponseHandler.response(500, null, null, e.getMessage(), false);
    }
  }

  @GetMapping
  // query params, ?status
  public ResponseEntity<?> getProducts(@RequestParam(value = "status", defaultValue = "") Boolean isDeleted) {
    return productService.getProductsService(isDeleted);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getProductById(@PathVariable String id) {
    return ResponseHandler.response(200, "success", productService.getProductByIdService(id), null, true);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateProductById(@PathVariable String id, @RequestBody ProductReq req) {
    return productService.updateProductByIdService(id, req);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteProductById(@PathVariable String id) {
    return productService.deleteProductByIdService(id);
  }
}
