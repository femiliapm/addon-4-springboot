package com.aocfazz.productAPI.payload.req;

import lombok.Data;

@Data
public class TransactionReq {
  private String status;
  private String customerId;
}
