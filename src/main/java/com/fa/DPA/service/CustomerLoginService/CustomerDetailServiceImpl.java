package com.fa.DPA.service.CustomerLoginService;

import com.fa.DPA.model.CustomerAccount;
import com.fa.DPA.repos.CustomerAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerDetailServiceImpl implements UserDetailsService {
    @Autowired
    CustomerAccountRepository customerAccountRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomerAccount cus = customerAccountRepository.findCustomerAccountByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with username : "+username));
        return CustomerDetailImpl.build(cus);
    }
}
