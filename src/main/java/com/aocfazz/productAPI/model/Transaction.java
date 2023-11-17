package com.aocfazz.productAPI.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
public class Transaction {
  @Id
  @UuidGenerator
  private String id;

  @CreationTimestamp
  private LocalDateTime createdAt;
  private Double totalAmount = 0.;
  private String status;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @UpdateTimestamp
  private LocalDateTime updatedAt;
  private Boolean isDeleted = false;

  public Transaction(String status, Customer customer) {
    this.status = status;
    this.customer = customer;
  }
}
