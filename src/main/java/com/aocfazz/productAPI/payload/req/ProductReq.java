package com.aocfazz.productAPI.payload.req;

import lombok.Data;

@Data
public class ProductReq {
  private String nama;
  private String deskripsi;
  private Double harga;
  private Integer kuantitas;
}
