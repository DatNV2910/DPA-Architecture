package com.fa.DPA.repos;

import com.fa.DPA.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;



import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends BaseRepository<News>{

    @Override
    @Query("SELECT n from News n order by n.id desc")
    Page<News> findAll(Pageable pageable);

    @Override
    Optional<News> findById(Long id);

    @Override
    List<News> findAll(Sort sort);

    @Override
    News save(News news);

    @Query("SELECT n from News n where n.isEnable= true order by n.id desc")
    Page<News> findAllwithstatusTrue(Pageable pageable);
}
