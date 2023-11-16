package com.aocfazz.productAPI.config;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    request.getHeader("Authorization");
    filterChain.doFilter(request, response);
    // // TODO Auto-generated method stub
    // 
    // throw new UnsupportedOperationException("Unimplemented method 'doFilterInternal'");
  }

}
