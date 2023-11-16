package com.aocfazz.productAPI.payload.req;

import lombok.Data;

@Data
public class OrderReq {
  private String productId;
  private Integer kuantitas;
}
