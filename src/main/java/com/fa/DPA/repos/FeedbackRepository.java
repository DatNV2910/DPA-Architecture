package com.fa.DPA.repos;

import com.fa.DPA.model.Feedback;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeedbackRepository extends BaseRepository<Feedback>{

    /**
     *
     * @param pageable
     * @return
     */
    @Override
    Page<Feedback> findAll(Pageable pageable);


    /**
     *
     * @param aLong
     * @return
     */
    @Override
    Optional<Feedback> findById(Long aLong);
}
