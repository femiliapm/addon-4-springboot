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
@Table(name = "customers")
@Data
@NoArgsConstructor
public class Customer {
  @Id
  @UuidGenerator
  private String id;
  private String firstName;
  private String lastName;

  @Column(unique = true)
  private String email;

  @Column(unique = true)
  private String username;

  @JsonIgnore
  private String password;

  @CreationTimestamp
  @JsonIgnore
  private LocalDateTime createdAt;

  @UpdateTimestamp
  @JsonIgnore
  private LocalDateTime updatedAt;
  private Boolean isActive = true;

  public Customer(String firstName, String lastName, String email, String username, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.username = username;
    this.password = password;
  }
}
