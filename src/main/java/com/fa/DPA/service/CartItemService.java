package com.fa.DPA.service;

import com.fa.DPA.model.CartItem;
import com.fa.DPA.repos.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CartItemService {

    private CartItemRepository cartItemRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository){
        this.cartItemRepository = cartItemRepository;
    }

    public List<CartItem> getAllCartItem(long user_id){
        try {
            return cartItemRepository.findAllByUserId(user_id);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    public CartItem saveItem(CartItem item){
        try{
             return cartItemRepository.save(item);
        } catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }

    public void removeItem(long[] ids){
        try{
            for (long id : ids) {
                cartItemRepository.deleteById(id);
            }
        } catch (Exception ex){
            System.out.println(ex);
        }
    }
    public CartItem findById(long id) {
        return cartItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Data with this id: " + id + " is not found"));
    }
}
