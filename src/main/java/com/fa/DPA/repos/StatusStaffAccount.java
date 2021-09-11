package com.fa.DPA.repos;

import com.fa.DPA.model.Account_Status;

import java.util.List;
import java.util.Optional;

public interface StatusStaffAccount extends BaseRepository<Account_Status>{
    @Override
    List<Account_Status> findAll();

    @Override
    Optional<Account_Status> findById(Long aLong);
}
