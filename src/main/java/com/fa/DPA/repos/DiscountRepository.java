package com.fa.DPA.repos;

import com.fa.DPA.model.Discount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DiscountRepository extends BaseRepository<Discount> {
    /**
     * @param pageable
     * @return
     */
    @Override
    @Query("select d from Discount d order by d.id desc")
    Page<Discount> findAll (Pageable pageable);

    Boolean existsById(String id);


    /**
     * @return
     */
    @Override
    long count();

}
