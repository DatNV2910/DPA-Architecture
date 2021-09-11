package com.fa.DPA.repos;

import com.fa.DPA.model.CustomerContact;
import com.fa.DPA.model.InteriorDesign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InteriorDesignRepository extends BaseRepository<InteriorDesign> {
    /**
     * @param pageable
     * @return
     */
    @Override
    @Query("SELECT interior from InteriorDesign interior order by interior.id desc")
    Page<InteriorDesign> findAll(Pageable pageable);

    Boolean existsById(String id);

    /**
     * @param aLong
     * @return
     */
    @Override
    Optional<InteriorDesign> findById(Long aLong);

    /**
     * @param s
     * @return
     */



    @Override
    <S extends InteriorDesign> S save(S s);


    @Override
    void deleteById(Long aLong);

    /**
     * @return
     */
    @Override
    long count();




}
