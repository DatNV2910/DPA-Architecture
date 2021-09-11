package com.fa.DPA.repos;

import com.fa.DPA.model.ModificationNews;
import org.springframework.stereotype.Repository;

@Repository
public interface ModifyNewsRepository extends BaseRepository<ModificationNews>{
    @Override
    ModificationNews save(ModificationNews mfNews);
}
