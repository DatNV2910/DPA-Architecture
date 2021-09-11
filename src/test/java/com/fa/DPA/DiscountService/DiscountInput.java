package com.fa.DPA.DiscountService;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.model.Discount;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class DiscountInput {
    private Pageable getAllDiscount_UTC01;
    private Pageable getAllDiscount_UTC02;
    private Discount saveDiscount_UTC01;
    private Discount saveDiscount_UTC02;
    private Discount saveDiscount_UTC03;
    private Discount saveDiscount_UTC04;
    private Discount saveDiscount_UTC05;
    private Discount saveDiscount_UTC06;

    public DiscountInput() {
        getAllDiscount_UTC01 = PageRequest.of(0, Constant.DEFAULT_PAGE_SIZE);
        getAllDiscount_UTC02 = null;

        saveDiscount_UTC01 = new Discount();
        saveDiscount_UTC01.setId((long) 1);
        saveDiscount_UTC01.setName("Discount name");
        saveDiscount_UTC01.setAmount(20);
        saveDiscount_UTC01.setDescription("This discount description");
        saveDiscount_UTC01.setCreateBy("Creator");

        saveDiscount_UTC02 = new Discount();
        saveDiscount_UTC02.setName("Discount name");
        saveDiscount_UTC02.setAmount(20);
        saveDiscount_UTC02.setDescription("This discount description");
        saveDiscount_UTC02.setCreateBy("Creator");

        saveDiscount_UTC03 = new Discount();
        saveDiscount_UTC03.setAmount(20);
        saveDiscount_UTC03.setDescription("This discount description");
        saveDiscount_UTC03.setCreateBy("Creator");

        saveDiscount_UTC04 = new Discount();
        saveDiscount_UTC04.setName("Discount name");
        saveDiscount_UTC04.setDescription("This discount description");
        saveDiscount_UTC04.setCreateBy("Creator");

        saveDiscount_UTC05 = new Discount();
        saveDiscount_UTC05.setName("Discount name");
        saveDiscount_UTC05.setAmount(20);
        saveDiscount_UTC05.setCreateBy("Creator");

        saveDiscount_UTC06 = null;
    }

    public Pageable getGetAllDiscount_UTC01() {
        return getAllDiscount_UTC01;
    }

    public Pageable getGetAllDiscount_UTC02() {
        return getAllDiscount_UTC02;
    }

    public Discount getSaveDiscount_UTC01() {
        return saveDiscount_UTC01;
    }

    public Discount getSaveDiscount_UTC02() {
        return saveDiscount_UTC02;
    }

    public Discount getSaveDiscount_UTC03() {
        return saveDiscount_UTC03;
    }

    public Discount getSaveDiscount_UTC04() {
        return saveDiscount_UTC04;
    }

    public Discount getSaveDiscount_UTC05() {
        return saveDiscount_UTC05;
    }

    public Discount getSaveDiscount_UTC06() {
        return saveDiscount_UTC06;
    }
}
