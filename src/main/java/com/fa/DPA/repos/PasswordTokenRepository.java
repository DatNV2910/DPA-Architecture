package com.fa.DPA.repos;

import com.fa.DPA.model.AbstractModel;
import com.fa.DPA.model.CustomerAccount;
import com.fa.DPA.model.PasswordResetToken;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordTokenRepository extends BaseRepository<PasswordResetToken>{

}
