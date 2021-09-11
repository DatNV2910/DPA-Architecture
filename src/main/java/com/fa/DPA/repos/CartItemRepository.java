package com.fa.DPA.repos;

import com.fa.DPA.model.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends BaseRepository<CartItem>{

    @Query("select c from CartItem c where c.customerAccount.id = :user_id")
    List<CartItem> findAllByUserId(long user_id);

    @Override
    CartItem save(CartItem cartItem);

    @Override
    void deleteById(Long id);
}
