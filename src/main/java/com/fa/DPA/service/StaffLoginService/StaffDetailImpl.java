package com.fa.DPA.service.StaffLoginService;

import com.fa.DPA.model.StaffAccount;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class StaffDetailImpl implements UserDetails {

    private static final long serialVersionUID= 1L;
    private Long id;
    private String username;
    private String email;

    @JsonIgnore
    private String password;


    private String role;

    public StaffDetailImpl(Long id,String username,String email,String password,
                           String role){
        this.id=id;
        this.username=username;
        this.email=email;
        this.password=password;
        this.role=role;
    }


    public static StaffDetailImpl build(StaffAccount staff){
        return new StaffDetailImpl(
            staff.getId(), staff.getUsername(),
                staff.getEmail(),  staff.getPassword()  ,staff.getRole().getName()
        );
    }

    //staff role - each staff has only one role : ROLE_ADMIN, ROLE_SALE, ROLE_CUSTOMERCARE
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
        //return Arrays.asList(new SimpleGrantedAuthority(role));
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if(o==null || getClass()!=o.getClass())
            return false;
        StaffDetailImpl staff= (StaffDetailImpl) o;
        return Objects.equals(id,staff.id);
    }
}
