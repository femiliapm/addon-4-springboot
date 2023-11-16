package com.aocfazz.productAPI.service.product;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.aocfazz.productAPI.model.Product;
import com.aocfazz.productAPI.payload.req.ProductReq;

public interface ProductService {
  // CRUD
  ResponseEntity<?> addProductService(ProductReq request);

  List<Product> getAllProductService();

  List<Product> getAllProductByIsDeletedService(Boolean isDeleted);

  ResponseEntity<?> getProductsService(Boolean isDeleted);

  Product getProductByIdService(String idProd);

  ResponseEntity<?> updateProductByIdService(String idProd, ProductReq request);

  ResponseEntity<?> deleteProductByIdService(String idProd);
}
