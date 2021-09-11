package com.fa.DPA.repos;

import com.fa.DPA.converter.StaffFreq;
import com.fa.DPA.converter.StaffSelect;
import com.fa.DPA.model.StaffAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffAccountRepository extends BaseRepository<StaffAccount>{
    /**
     *
     * @param aLong
     * @return
     */
    @Override
    Optional<StaffAccount> findById(Long aLong);

    /**
     *
     * @param pageable
     * @return
     */
    @Override
    Page<StaffAccount> findAll(Pageable pageable);

    @Query(value="SELECT s.id as staffId , s.staff_name as staffName, s.role_id as staffRoleId " +
            " FROM staff_account s where role_id = :roleId", nativeQuery=true)
    List<StaffSelect> findAllWithRole(long roleId);


    /**
     *
     * @param id
     * @return
     */
    boolean existsStaffAccountById(Long id);

    /**
     * find Staff account by username.
     * @param username
     * @return
     */
    Optional<StaffAccount> findStaffAccountByUsername(String username);

    /**
     * check account exist or not
     * @param username
     * @return
     */
    Boolean existsByUsername(String username );

    /**
     * check email exist or not
     * @param email
     * @return
     */
    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);

    /**
     *
     * @param year
     * @return
     */
    @Query(value="select s.id as staffId, s.staff_name as staffName, count(o.staff_id) as freq \n" +
            "from dpa_web.orders o inner join staff_account s on o.staff_id = s.id where o.finished_date is not null \n" +
            "and year(o.finished_date) = :year \n" +
            "group by staffId order by freq desc limit 5", nativeQuery=true)
    List<StaffFreq> findStaffFreqOnlyYear(int year);

    /**
     *
     * @param year
     * @return
     */
    @Query(value="select s.id as staffId, s.staff_name as staffName, count(o.staff_id) as freq \n" +
            "from dpa_web.orders o inner join staff_account s on o.staff_id = s.id where o.finished_date is not null \n" +
            "and year(o.finished_date) = :year and month(o.finished_date) = :month \n" +
            "group by staffId order by freq desc limit 5", nativeQuery=true)
    List<StaffFreq> findStaffFreqYearAndMonth(int year, int month);

}
