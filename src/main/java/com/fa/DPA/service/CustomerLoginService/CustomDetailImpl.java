package com.fa.DPA.service.CustomerLoginService;

import com.fa.DPA.model.CustomerAccount;
import com.fa.DPA.model.StaffAccount;
import com.fa.DPA.repos.CustomerAccountRepository;
import com.fa.DPA.repos.StaffAccountRepository;
import com.fa.DPA.service.StaffLoginService.StaffDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomDetailImpl implements UserDetailsService {

    @Autowired
    CustomerAccountRepository customerAccountRepository;

    @Autowired
    StaffAccountRepository staffAccountRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CustomerAccount> cus= customerAccountRepository.findCustomerAccountByUsername(username);
        Optional<StaffAccount> staff= staffAccountRepository.findStaffAccountByUsername(username);
        if(cus.isPresent()){
            System.out.println("This is customer : "+cus.get().getUsername());
            return CustomerDetailImpl.build(cus.get());
        }else if(staff.isPresent()){
            System.out.println("This is staff : "+staff.get().getUsername());
            return StaffDetailImpl.build(staff.get());
        }
        return null;
    }
}
