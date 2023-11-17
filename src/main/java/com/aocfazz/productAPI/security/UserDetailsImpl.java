package com.aocfazz.productAPI.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.aocfazz.productAPI.model.Customer;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
  private Collection<? extends GrantedAuthority> authorities;
  private String password;
  private String email;
  private Boolean isActive;

  public static UserDetails build(Customer customer) {
    return new UserDetailsImpl(AuthorityUtils.createAuthorityList("ROLE_USER"), customer.getPassword(),
        customer.getEmail(), customer.getIsActive());
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return this.isActive;
  }

}
