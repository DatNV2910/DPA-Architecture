package com.fa.DPA.repos;

import com.fa.DPA.model.CdOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CdOrderRepository extends JpaRepository<CdOrder, Long> {
    /**
     *
     * @param pageable
     * @param ids
     * @return
     */
    @Query("select cd from CdOrder cd inner join Order o on cd.order.id = o.id" +
            " where o.status.id in :ids and o.owner.id = :id order by o.id desc")
    Page<CdOrder> findAllCustomize(Pageable pageable, long id ,long[] ids);

    /**
     * @param id
     * @return
     */
    @Query(value="SELECT * FROM dpa_web.cd_order where order_id = :id", nativeQuery = true)
    List<CdOrder> findAllByOrder_Id(long id);

    @Override
    <S extends CdOrder> S save(S s);

    /**
     *
     * @param id
     * @param ids
     * @return
     */
    @Query("select cd from CdOrder cd inner join Order o on cd.order.id = o.id"+
            " where o.status.id in :ids and o.owner.id = :id order by o.id desc")
    List<CdOrder> findAllWithStatus(long id, long[] ids);
}
