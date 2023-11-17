package com.aocfazz.productAPI.payload.req;

import lombok.Data;

@Data
public class CustomerReq {
  private String fname;
  private String lname;
  private String email;
  private String username;
  private String password;
}
