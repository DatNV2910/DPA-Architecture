package com.fa.DPA.repos;


import com.fa.DPA.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends BaseRepository<Role>{
    /**
     *
     * @return
     */
    @Override
    List<Role> findAll();

    @Override
    Optional<Role> findById(Long aLong);

    Optional<Role> findRoleByName(String name);

}
