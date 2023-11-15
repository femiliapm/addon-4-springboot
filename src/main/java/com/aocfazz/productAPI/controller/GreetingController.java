package com.aocfazz.productAPI.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aocfazz.productAPI.payload.req.LoginReq;

@RestController
public class GreetingController {
  @GetMapping("/hello")
  public String hello() {
    return "Hello World!";
  }

  // query params ?key=value
  @GetMapping("/hello2")
  public String hello2(@RequestParam(value = "name", defaultValue = "") String name) {
    return "Hello " + name + "!";
  }

  // path variable
  // url/id
  @GetMapping("/hello3/{id}")
  public String hello3(@PathVariable(value = "id", required = false) String uuid) {
    return "idnya adalah " + uuid;
  }

  // request body
  @PostMapping("/hello4")
  public String hello4(@RequestBody String payload) {
    return payload;
  }

  @PostMapping("/hello5")
  public ResponseEntity<LoginReq> hello4(@RequestBody LoginReq payload) {
    // bisa diisi logika codenya
    return ResponseEntity.status(HttpStatus.ACCEPTED.value()).body(payload);
  }
}
