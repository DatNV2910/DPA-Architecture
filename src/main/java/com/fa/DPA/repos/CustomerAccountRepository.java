package com.fa.DPA.repos;

import com.fa.DPA.model.CustomerAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerAccountRepository extends BaseRepository<CustomerAccount>{

    /**
     *
     * @param aLong
     * @return
     */
    @Override
    Optional<CustomerAccount> findById(Long aLong);

    /**
     *
     * @param pageable
     * @return
     */
    @Override
    @Query("select c from CustomerAccount c order by c.id desc")
    Page findAll(Pageable pageable);

    Optional<CustomerAccount> findCustomerAccountByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);

    Boolean existsById(String id);


    /**
     *
     * @param email
     * @return
     */
    @Query("select c from CustomerAccount c where c.email = :email")
    Optional<CustomerAccount> findByEmail(String email);


    /**
     *
     * @param token
     * @param timeNow
     * @return
     */
    @Query("select c from CustomerAccount  c inner join PasswordResetToken p on c.id = p.user.id " +
            "where p.token = :token and p.expiryTime >= :timeNow")
    Optional<CustomerAccount> findByResetPasswordToken(String token, LocalDateTime timeNow);


}
