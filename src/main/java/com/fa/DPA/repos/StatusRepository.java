package com.fa.DPA.repos;

import com.fa.DPA.model.Status;

import java.util.List;

public interface StatusRepository extends BaseRepository<Status>{
    /**
     *
     * @return
     */
    @Override
    List findAll();
}
