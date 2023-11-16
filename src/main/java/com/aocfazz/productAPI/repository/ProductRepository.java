package com.aocfazz.productAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aocfazz.productAPI.model.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
  // query methods: select * from products where is_deleted = true
  List<Product> findByIsDeleted(Boolean status);

  // native query
  @Query(value = "select * from products where is_deleted = ?", nativeQuery = true)
  List<Product> findByStatus(Boolean status);
  // @Query(value = "select * from products where is_deleted = ?1 and id = ?2")
  // List<Product> findByStatus(Boolean status, String id);
}
