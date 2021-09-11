package com.fa.DPA.service;

import com.fa.DPA.model.ConstructionDrawing;
import com.fa.DPA.model.Discount;
import com.fa.DPA.repos.ConstructionDrawingRepository;
import com.fa.DPA.repos.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiscountService {

    private DiscountRepository discountRepository;
    private ConstructionDrawingRepository constructionDrawingRepository;
    @Autowired
    public DiscountService(DiscountRepository discountRepository, ConstructionDrawingRepository constructionDrawingRepository) {
        this.discountRepository = discountRepository;
        this.constructionDrawingRepository = constructionDrawingRepository;
    }





    public Page<Discount> getAllDiscount(Pageable pageable) {
        try {
            return discountRepository.findAll(pageable);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    public Discount saveDiscount(Discount discount) {
        try {
            Discount discountReturn;
            if (discount.getId() != null) {
                Discount oldDiscount = discountRepository.findById(discount.getId()).orElse(new Discount());
                oldDiscount.setName(discount.getName());
                oldDiscount.setAmount(discount.getAmount());
                oldDiscount.setDescription(discount.getDescription());
//                oldDiscount.setConstructionDrawing(discount.getConstructionDrawing());
                discountReturn = oldDiscount;
            } else {
                discountReturn = discount;
            }
            discountReturn = discountRepository.save(discountReturn);
            return discountReturn;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    public Discount findById(Long id) {
        return discountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Data with this id: " + id + " is not found"));
    }


}