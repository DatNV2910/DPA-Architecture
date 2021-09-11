package com.fa.DPA.repos;

import com.fa.DPA.converter.OrderOverview;
import com.fa.DPA.converter.Revenue;
import com.fa.DPA.model.Order;
import com.fa.DPA.model.StaffAccount;
import com.fa.DPA.model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface OrderRepository extends BaseRepository<Order> {

    /**
     *
     * @return
     */
    @Query("Select o from Order o where o.staffAccount.staff_name like %:staffName%")
    Page<Order> findAllWithStaffName(String staffName, Pageable p);

    /**
     *
     * @param year
     * @param id
     * @return
     */
    @Query(value="SELECT month(o.finished_date) as month_finish, Sum(total_price) as revenue " +
            "FROM dpa_web.orders o where o.finished_date is not null and year(o.finished_date) = :year " +
            "and o.staff_id = :id group by month_finish order by month_finish asc" , nativeQuery=true)
    List<Revenue> getSumRevenueForEachYearByStaff(int year, long id);


    /**
     *
     * @param pageable
     * @return
     */
    @Query("select o from Order o where o.staffAccount.id is null order by o.id desc")
    Page<Order> findAllWait(Pageable pageable);

    /**
     *
     * @param p
     * @return
     */
    @Query(value="select * from orders order by (status_id = 3) desc, id desc", nativeQuery=true)
    Page<Order> findAllPage(Pageable p);

    /**
     *
     * @param id
     * @return
     */
    boolean existsOrderById(Long id);

    /**
     *
     * @param staffAccount
     * @param status
     * @return
     */
    int countOrderByStaffAccountAndStatus(StaffAccount staffAccount, Status status);


    /**
     *
     * @param year
     * @return
     */
    @Query(value="SELECT month(o.finished_date) as month_finish, Sum(total_price) as revenue " +
            "FROM dpa_web.orders o where o.finished_date is not null and year(o.finished_date) = :year " +
            "group by month_finish order by month_finish asc" , nativeQuery=true)
    List<Revenue> getSumRevenueForEachYear(int year);





    /**
     *
     * @param user_id
     * @return
     */
    @Query(value = "SELECT * FROM orders where owner_id = :user_id ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Order findByOwnerId(long user_id);

    /**
     * @return
     */
    @Query("select count(o) from Order o WHERE o.status.id = 1 and o.finishedDate is not null and o.staffAccount.id = :id")
    Long countNumberFinishOrder(Long id);

    /**
     *
     * @return
     */
    @Query(value = "select count(*) from dpa_web.orders where orders.status_id = 2 " +
            "and year(orders.finished_date) = 2021 and month(orders.finished_date) = 5", nativeQuery = true)
    Integer countNumberCancelOrder(int year, int month);

    /**
     *
     * @param year
     * @param month
     * @return
     */
    @Query(value="SELECT Sum(total_price) " +
            "FROM dpa_web.orders o where o.finished_date is not null and year(o.finished_date) = :year and " +
            " month(o.finished_date) = :month" , nativeQuery=true)
    BigDecimal countRevenueByCurrentMonth(int year, int month);


    /**
     *
     * @param year
     * @param month
     * @return
     */
    @Query(value="select count(*) from dpa_web.orders where year(orders.finished_date) = :year " +
            "and month(orders.finished_date) = :month", nativeQuery = true)
    Long countOrderInCurrentMonth(int year, int month);

    @Query(value = "Select * from (\n" +
            "(Select count(*) as cancel from orders where orders.status_id = 2 and year(orders.created_date) = :year) as q1\n" +
            "cross join ( Select count(*) as finish from orders where orders.status_id = 1 and year(orders.created_date) = :year ) as q2\n" +
            "cross join (Select count(*) as processing from orders where orders.status_id = 4 and year(orders.created_date) = :year) as q3\n" +
            "cross join (Select count(*) as pending from orders where orders.status_id = 3 and year(orders.created_date) = :year) as q4 ) ", nativeQuery = true)
    OrderOverview getOrderOverviewByYear(int year);



    /**
     *
     * @param year
     * @return
     */
    @Query(value="Select count(*) from orders o where year(o.created_date) = :year", nativeQuery=true)
    Long countAllByYear(int year);

    /**
     *
     * @param status
     * @param pageable
     * @return
     */
    Page<Order> findAllByStatus(Status status, Pageable pageable);

    /**
     *
     * @param staffAccountName
     * @param statusId
     * @param pageable
     * @return
     */
    @Query("select o from Order o where o.staffAccount.staff_name like %:staffAccountName% and o.status.id = :statusId")
    Page<Order> findAllWithStaffNameAndStatus(String staffAccountName, Long statusId, Pageable pageable);

}

