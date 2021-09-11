package com.fa.DPA.repos;

import com.fa.DPA.model.ConstructionDrawing;
import com.fa.DPA.model.Discount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ConstructionDrawingRepository extends BaseRepository<ConstructionDrawing> {

    /**
     *
     * @param code
     * @return
     */
    Optional<ConstructionDrawing> findOneByCode (String code);

    /**
     *
     * @param pageable
     * @return
     */
    @Query("select c from ConstructionDrawing c where c.status = true")
    Page<ConstructionDrawing> findAllByStatusTrue(Pageable pageable);

    /**
     *
     * @param pageable
     * @return
     */
    @Query(value = "Select * from construction_drawing c left outer join cd_order cd " +
            " on c.id = cd.cd_id  where c.status = 1 group by c.id order by count(c.id) desc", nativeQuery = true)
    Page<ConstructionDrawing> findWithHighestSold(Pageable pageable);

    Boolean existsById(String id);

    /**
     *
     * @param pageable
     * @param search
     * @return
     */

    @Query(value = "Select * from construction_drawing c left outer join cd_order cd " +
            " on c.id = cd.cd_id  where c.name like %:search% and c.status = 1 group by c.id order by count(c.id) desc", nativeQuery = true)
    Page<ConstructionDrawing> findWithHighestSoldWithSearch(Pageable pageable, String search);

    /**
     *
     * @param pageable
     * @param search
     * @return
     */
    @Query("select c from ConstructionDrawing c where c.status = true and c.name like %:search%")
    Page<ConstructionDrawing> findAllByStatusTrueWithSearch(Pageable pageable, String search);

    /**
     *
     * @param pageable
     * @param filter
     * @return
     */
    @Query("select c from ConstructionDrawing c where c.status = true and c.subCategory.name = :filter")
    Page<ConstructionDrawing> findAllByStatusTrueWithFilter(Pageable pageable, String filter);

    /**
     *
     * @param pageable
     * @param search
     * @param filter
     * @return
     */
    @Query("select c from ConstructionDrawing c where c.status = true and c.name like %:search% and c.subCategory.name = :filter")
    Page<ConstructionDrawing> findAllByStatusTrueWithSearchAndFilter(Pageable pageable, String search, String filter);

    /**
     *
     * @param p
     * @param search
     * @return
     */
    @Query("select c from ConstructionDrawing c where c.name like %:search%")
    Page<ConstructionDrawing> findAllWithSearch(Pageable p, String search);

    /**
     *
     * @param p
     * @param filter
     * @return
     */
    @Query("select c from ConstructionDrawing c where c.subCategory.name = :filter")
    Page<ConstructionDrawing> findAllWithFilter(Pageable p, String filter);


    /**
     *
     * @param p
     * @param search
     * @param filter
     * @return
     */
    @Query("select c from ConstructionDrawing c where c.subCategory.name like %:search% and c.subCategory.name = :filter")
    Page<ConstructionDrawing> findAllWithSearchAndFilter(Pageable p,String search ,String filter);
}
