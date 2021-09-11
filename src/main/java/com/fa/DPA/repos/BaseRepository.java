package com.fa.DPA.repos;

import com.fa.DPA.model.AbstractModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T extends AbstractModel> extends JpaRepository<T, Long>{
    /**
     *
     * @return
     */
    @Override
    List<T> findAll();

    /**
     *
     * @param sort
     * @return
     */
    @Override
    List<T> findAll(Sort sort);

    /**
     *
     * @param iterable
     * @return
     */
    @Override
    List<T> findAllById(Iterable<Long> iterable);

    /**
     *
     * @param pageable
     * @return
     */
    @Override
    Page<T> findAll(Pageable pageable);

    /**
     *
     * @param s
     * @param <S>
     * @return
     */
    @Override
    <S extends T> S save(S s);


    /**
     *
     * @param aLong
     * @return
     */
    @Override
    Optional<T> findById(Long aLong);

    /**
     *
     * @return
     */
    @Override
    long count();



}
