package com.fa.DPA.service.StaffLoginService;

import com.fa.DPA.model.StaffAccount;
import com.fa.DPA.repos.StaffAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StaffDetailsServiceImpl implements UserDetailsService {

    @Autowired
    StaffAccountRepository staffAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username)  {
        StaffAccount staff = staffAccountRepository.findStaffAccountByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with username : "+username));
        return StaffDetailImpl.build(staff);
    }
}
