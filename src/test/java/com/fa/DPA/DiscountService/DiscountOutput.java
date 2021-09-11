package com.fa.DPA.DiscountService;

import com.fa.DPA.model.Discount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

public class DiscountOutput {
    private Page<Discount> getAllDiscount_UTC01;
    private Page<Discount> getAllDiscount_UTC02;
    private Discount saveDiscount_UTC01;
    private Discount saveDiscount_UTC03;

    public DiscountOutput() {
        List<Discount> discountList = new ArrayList<>();
        discountList.add(new Discount());
        getAllDiscount_UTC01 = new PageImpl(discountList);
        getAllDiscount_UTC02 = null;

        saveDiscount_UTC01 = new Discount();
        saveDiscount_UTC01.setId((long) 1);
        saveDiscount_UTC01.setName("Discount name");
        saveDiscount_UTC01.setAmount(20);
        saveDiscount_UTC01.setDescription("This discount description");
        saveDiscount_UTC01.setCreateBy("Creator");

        saveDiscount_UTC03 = null;
    }

    public Page<Discount> getGetAllDiscount_UTC01() {
        return getAllDiscount_UTC01;
    }

    public Page<Discount> getGetAllDiscount_UTC02() {
        return getAllDiscount_UTC02;
    }

    public Discount getSaveDiscount_UTC01() {
        return saveDiscount_UTC01;
    }

    public Discount getSaveDiscount_UTC03() {
        return saveDiscount_UTC03;
    }
}
