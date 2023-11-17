package com.aocfazz.productAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aocfazz.productAPI.payload.res.ResponseHandler;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class CustomExceptionHandler {
  // global exception -> Exception
  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<?> handleException(Exception e) {
    return ResponseHandler.response(500, e.getMessage(), null, e.getCause(), false);
  }

  @ExceptionHandler(value = EntityExistsException.class)
  public ResponseEntity<?> handleEntityExistsEx(EntityExistsException e) {
    return ResponseHandler.response(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage(), null, e.getCause(), false);
  }

  @ExceptionHandler(value = EntityNotFoundException.class)
  public ResponseEntity<?> handleEntityNotFoundEx(EntityNotFoundException e) {
    return ResponseHandler.response(HttpStatus.NOT_FOUND.value(), e.getMessage(), null, e.getCause(), false);
  }
}
