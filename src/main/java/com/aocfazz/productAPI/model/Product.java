package com.aocfazz.productAPI.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {
  @Id
  @UuidGenerator
  private String id;

  @Column(nullable = false, length = 100)
  private String name;
  private String description;

  @Column(nullable = false)
  private Double price = 0.;

  @Column(nullable = false)
  private Integer quantity = 0;

  @CreationTimestamp
  @JsonIgnore
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;
  private Boolean isDeleted = false;

  public Product(String name, String description, Double price, Integer quantity) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.quantity = quantity;
  }
}
