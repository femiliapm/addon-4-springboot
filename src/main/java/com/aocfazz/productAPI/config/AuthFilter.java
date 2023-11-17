package com.aocfazz.productAPI.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.aocfazz.productAPI.security.UserDetailsServiceImpl;
import com.aocfazz.productAPI.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFilter extends OncePerRequestFilter {
  @Autowired
  JwtUtil jwtUtil;

  @Autowired
  UserDetailsServiceImpl userDetailsServiceImpl;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    /*
     * check setiap req punya header Authorization atau tidak?
     */
    String headerAuth = request.getHeader("Authorization");
    if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
      /*
       * apakah tokennya ada atau tidak?
       */
      String token = headerAuth.substring(7);
      if (token != null && jwtUtil.validateToken(token)) {
        /*
         * claim email untuk authent user tsb
         */
        String email = jwtUtil.getEmailFromToken(token);
        if (email != null) {
          UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(email);
          UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
              userDetails.getPassword(), userDetails.getAuthorities());

          authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

          SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
      }
    }

    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
    response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");

    if (request.getMethod().equals("OPTIONS")) {
      response.setStatus(200);
    } else {
      filterChain.doFilter(request, response);
    }
  }

}
