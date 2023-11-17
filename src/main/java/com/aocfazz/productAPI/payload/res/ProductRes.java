package com.aocfazz.productAPI.payload.res;

public interface ProductRes {
  // select id, description, name, price, quantity, updated_at as updatedAt from
  // products;
  String getId();

  String getDescription();

  String getName();

  String getPrice();

  String getQuantity();

  String getUpdatedAt();
}
