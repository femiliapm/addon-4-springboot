package com.aocfazz.productAPI.payload.req;

import lombok.Data;

@Data
public class LoginReq {
  private String email;
  private String password;
}
