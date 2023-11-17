package com.aocfazz.productAPI.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {
  @Autowired
  UserDetailsService userDetailsService;

  @Autowired
  AuthFilter authFilter;

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // disable csrf
    http.csrf(csrf -> {
      csrf.disable();
    });

    // session management
    http.sessionManagement(session -> {
      session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    });

    // authorize
    http.authorizeHttpRequests(auth -> {
      auth.requestMatchers("/customers/login").permitAll()
          .requestMatchers(HttpMethod.POST, "/customers").permitAll()
          // .requestMatchers("/products").hasRole("ROLE_USER")
          .anyRequest().fullyAuthenticated();
    });

    // provider
    http.authenticationProvider(authenticationProvider());

    // filter
    http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /*
   * provider: manager untuk mengautentikasi user saat datang request / login
   */
  @Bean
  AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
      throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  /*
   * dao provider: provider untuk mencari email dan mencocokannya
   */
  @Bean
  DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(passwordEncoder());
    authenticationProvider.setUserDetailsService(userDetailsService);
    return authenticationProvider;
  }
}
