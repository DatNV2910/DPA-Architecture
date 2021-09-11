package com.fa.DPA.converter;

import com.fa.DPA.dto.CustomerAccountDTO;

import com.fa.DPA.model.CustomerAccount;

import org.springframework.stereotype.Component;

@Component
public class CustomerAccountConverter {
    public CustomerAccountDTO toDto(CustomerAccount entity){
        CustomerAccountDTO dto = new CustomerAccountDTO();
        if(entity.getId() != null) {
            dto.setId(entity.getId());
        }
        dto.setAccount_name(entity.getAccount_name());
        dto.setAddress(entity.getAddress());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setUsername(entity.getUsername());
        dto.setAvatarSource(entity.getAvatarSource());
        dto.setCreate_date(entity.getCreate_date());
        return dto;
    }
}
