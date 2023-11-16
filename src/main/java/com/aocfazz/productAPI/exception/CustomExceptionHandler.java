package com.aocfazz.productAPI.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aocfazz.productAPI.payload.res.ResponseHandler;

@RestControllerAdvice
public class CustomExceptionHandler {
  // global exception -> Exception
  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<?> handleException(Exception e) {
    return ResponseHandler.response(500, e.getMessage(), null, e.getCause(), false);
  }
}
