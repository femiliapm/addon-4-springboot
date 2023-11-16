package com.aocfazz.productAPI.payload.res;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.ResponseEntity;

public class ResponseHandler {
  public static ResponseEntity<?> response(Integer statusCode, String message, Object data, Object error,
      Boolean isSuccess) {
    // response body
    Map<String, Object> body = new HashMap<>();
    body.put("status", statusCode);
    body.put("message", message);
    body.put("success", isSuccess);

    if (data != null || Objects.nonNull(data)) {
      body.put("data", data);
    }

    if (error != null || Objects.nonNull(error)) {
      body.put("error", error);
      body.put("timestamp", LocalDateTime.now());
    }

    return ResponseEntity.status(statusCode).body(body);
  }
}
