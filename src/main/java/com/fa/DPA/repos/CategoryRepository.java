package com.fa.DPA.repos;

import com.fa.DPA.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.StyledEditorKit;

public interface CategoryRepository extends BaseRepository<Category> {

    /**
     * @param pageable
     * @return
     */
    @Override
    @Query("select c from Category c order by c.id desc")
    Page<Category> findAll(Pageable pageable);

    @Query(value = "select c from Category c where c.type.id = 1")
    Page<Category>findWithInteriorDesign(Pageable pageable);

    @Query(value = "select c from Category c where c.type.id = 2")
    Page<Category>findWithConstruction(Pageable pageable);

    Boolean existsById(String id);



}
