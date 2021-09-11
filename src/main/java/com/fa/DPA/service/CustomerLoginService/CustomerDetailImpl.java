package com.fa.DPA.service.CustomerLoginService;

import com.fa.DPA.model.CustomerAccount;
import com.fa.DPA.service.StaffLoginService.StaffDetailImpl;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomerDetailImpl implements UserDetails {

    private static final long serialVersionUID= 1L;
    private Long id;
    private String username;
    private String email;

    @JsonIgnore
    private String password;




    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public CustomerDetailImpl(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public static CustomerDetailImpl build(CustomerAccount cus){
        return new CustomerDetailImpl(
                cus.getId(), cus.getUsername(),
                cus.getEmail(),  cus.getPassword()
        );
    }

    //should we set role for customer or not ???
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return true;
    }
}
