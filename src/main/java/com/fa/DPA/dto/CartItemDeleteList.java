package com.fa.DPA.dto;

import lombok.Data;

@Data
public class CartItemDeleteList {
    private long[] ids;

    public CartItemDeleteList(long[] ids) {
        this.ids = ids;
    }

    public CartItemDeleteList() {
    }

}
