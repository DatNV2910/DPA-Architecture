package com.fa.DPA.repos;

import com.fa.DPA.model.InteriorDesign;
import com.fa.DPA.model.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface SubCategoryRepository extends BaseRepository<SubCategory> {
    /**
     * @param aLong
     * @return
     */
    @Override
    Optional<SubCategory> findById(Long aLong);

    Boolean existsById(String id);

    @Query("select c from SubCategory c where c.category.id = :category_id")
    Page<SubCategory> findByCategory(long category_id, Pageable pageable);

    /**
     * @param pageable
     * @return
     */
    @Override
    @Query("select s from SubCategory s order by s.id desc")
    Page<SubCategory> findAll(Pageable pageable);
}
