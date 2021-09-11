package com.fa.DPA.dto;

import com.fa.DPA.model.CartItem;
import com.fa.DPA.model.Order;
import lombok.Data;

import java.util.List;

@Data
public class CheckOutFormDTO {
    private String address;
    private String phone;
    private List<Long> cartItemIds;
    private String[] area_size;

    public CheckOutFormDTO(String address, String phone, List<Long> cartItemIds, String[] area_size) {
        this.address = address;
        this.phone = phone;
        this.cartItemIds = cartItemIds;
        this.area_size = area_size;
    }
}
