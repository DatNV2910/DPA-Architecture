package com.fa.DPA.CartItemService;

import com.fa.DPA.model.*;
import org.springframework.data.domain.Pageable;

public class CartItemInput {
    private long getAllCartItem_UTC01;

    private long getAllCartItem_UTC02;

    private Long getAllCartItem_UTC03;

    private CartItem saveItem_UTC01;

    private CartItem saveItem_UTC02;

    private CartItem saveItem_UTC03;

    private CartItem saveItem_UTC04;

    public CartItemInput() {
        getAllCartItem_UTC01 = 1;
        getAllCartItem_UTC02 = -1;
        getAllCartItem_UTC03 = null;

        CustomerAccount test_customer = new CustomerAccount();
        test_customer.setId((long) 1);
        test_customer.setAccount_name("test customer");
        test_customer.setEmail("testcustomer123@gmail.com");
        test_customer.setPhone("0123456789");
        test_customer.setAddress("street XX, Hanoi, Vietnam");
        test_customer.setAvatarSource("source");
        test_customer.setPassword("password123");
        test_customer.setUsername(("testcustomer123"));

        ConstructionDrawing test_cd = new ConstructionDrawing();
        test_cd.setId((long) 1);
        test_cd.setStatus(true);
        test_cd.setCode("code");
        test_cd.setDescription("description of this cd");
        test_cd.setDiscount(new Discount());
        test_cd.setImgSource("img-source");
        test_cd.setName("test cd");
        test_cd.setNumberFloor(3);
        test_cd.setPrice(250000);
        test_cd.setSubCategory(new SubCategory());
        test_cd.setThumbnail("thumbnail");

        saveItem_UTC01 = new CartItem();
        saveItem_UTC01.setConstructionDrawing(test_cd);
        saveItem_UTC01.setCustomerAccount(test_customer);

        saveItem_UTC02 = new CartItem();
        saveItem_UTC02.setConstructionDrawing(test_cd);

        saveItem_UTC03 = new CartItem();
        saveItem_UTC03.setCustomerAccount(test_customer);

        saveItem_UTC04 = null;
    }

    public CartItem getSaveItem_UTC01() {
        return saveItem_UTC01;
    }

    public CartItem getSaveItem_UTC02() {
        return saveItem_UTC02;
    }

    public CartItem getSaveItem_UTC03() {
        return saveItem_UTC03;
    }

    public CartItem getSaveItem_UTC04() {
        return saveItem_UTC04;
    }

    public long getGetAllCartItem_UTC01() {
        return getAllCartItem_UTC01;
    }

    public long getGetAllCartItem_UTC02() {
        return getAllCartItem_UTC02;
    }

    public Long getGetAllCartItem_UTC03() {
        return getAllCartItem_UTC03;
    }
}
