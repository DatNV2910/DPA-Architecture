package com.fa.DPA.service;

import com.fa.DPA.converter.CustomerAccountConverter;
import com.fa.DPA.dto.CustomerAccountDTO;
import com.fa.DPA.model.CustomerAccount;
import com.fa.DPA.repos.CustomerAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerAccountService {
    private CustomerAccountRepository customerAccountRepository;

    @Autowired
    public CustomerAccountService(CustomerAccountRepository customerAccountRepository) {
        this.customerAccountRepository = customerAccountRepository;
    }
    @Autowired
    private CustomerAccountConverter converter;
    /**
     *
     * @param id
     * @return
     */
    public CustomerAccount findById(Long id){
        return customerAccountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Data with this id: " + id + " is not found"));
    }

    /**
     *
     * @param email
     * @return
     */
    public CustomerAccount findByEmail(String email){
        return customerAccountRepository.findByEmail(email).
                orElseThrow(() -> new EntityNotFoundException("Data with this email: " + email + " is not found"));
    }
    /**
     *
     * @param token
     * @param localDateTime
     * @return
     */
    public CustomerAccount findByTokenPassword(String token, LocalDateTime localDateTime){
        return customerAccountRepository.findByResetPasswordToken(token, localDateTime)
                .orElseThrow(() -> new EntityNotFoundException("Data with this token: " + token + " is not found or unavailable"));
    }

    /**
     *
     * @param customerAccount
     * @return
     */
    public CustomerAccount saveAccount(CustomerAccount customerAccount){
        try{
            customerAccountRepository.save(customerAccount);
            return customerAccount;
        }catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }


    /**
     *
     * @param username
     * @return
     */
    public CustomerAccount findStaffAccountByUsername(String username) {
        return customerAccountRepository.findCustomerAccountByUsername(username).
                orElseThrow(() -> new EntityNotFoundException("Data with this username: " + username + " is not found"));
    }

    public List<CustomerAccountDTO> getAllCustomerAccount(Pageable pageable){
        try{
            List<CustomerAccountDTO> result = new ArrayList<>();
            List<CustomerAccount> customerAccounts = customerAccountRepository.findAll(pageable).getContent();
            for (CustomerAccount items : customerAccounts){
                CustomerAccountDTO dto = converter.toDto(items);
                result.add(dto);
            }
            return result;
        } catch (Exception exception){
            System.out.println(exception);
        }
        return null;
    }
    public int totalItems(){
        return (int) customerAccountRepository.count();
    }
}
