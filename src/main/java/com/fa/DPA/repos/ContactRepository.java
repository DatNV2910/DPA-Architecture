package com.fa.DPA.repos;

import com.fa.DPA.model.CustomerContact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends BaseRepository<CustomerContact>{

    /**
     *
     * @param pageable
     * @return
     */
    @Override
    @Query("Select c from CustomerContact c order by c.id desc")
    Page<CustomerContact> findAll(Pageable pageable);

    Boolean existsById(String id);
}
