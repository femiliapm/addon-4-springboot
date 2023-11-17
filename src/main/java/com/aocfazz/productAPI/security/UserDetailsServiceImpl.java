package com.aocfazz.productAPI.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aocfazz.productAPI.model.Customer;
import com.aocfazz.productAPI.repository.CustomerRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  CustomerRepository customerRepository;

  public UserDetailsServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (!customerRepository.existsByEmail(username)) {
      throw new UsernameNotFoundException(username + " is not found!");
    }

    Customer customer = customerRepository.findByEmail(username);

    return UserDetailsImpl.build(customer);
  }

}
