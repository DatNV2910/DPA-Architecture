package com.fa.DPA.service;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.model.ConstructionDrawing;
import com.fa.DPA.model.Order;
import com.fa.DPA.model.Status;
import com.fa.DPA.repos.ConstructionDrawingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ConstructionDrawingService {
    private ConstructionDrawingRepository constructionDrawingRepository;

    @Autowired
    public ConstructionDrawingService(ConstructionDrawingRepository constructionDrawingRepository) {
        this.constructionDrawingRepository = constructionDrawingRepository;
    }

    /**
     *
     * @param pageable
     * @return
     */
    public Page<ConstructionDrawing> getAllPage(Pageable pageable, int out_size, boolean isStaff){
        try{
            switch (out_size){
                case -1:
                    if(isStaff){
                        return constructionDrawingRepository.findAll(pageable);
                    }else{
                        return constructionDrawingRepository.findAllByStatusTrue(pageable);
                    }
                case 0:
                    return constructionDrawingRepository.findWithHighestSold(pageable);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    public ConstructionDrawing findById(long id){
        return constructionDrawingRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Data with this id: " + id + " is not found"));
    }

    /**
     *
     * @param constructionDrawing
     * @return
     */
    public ConstructionDrawing save(ConstructionDrawing constructionDrawing) {
        try{
            return constructionDrawingRepository.save(constructionDrawing);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }


    /**
     *
     * @param id
     * @throws EntityNotFoundException
     */
    public void softDelete(Long id) throws EntityNotFoundException{
        ConstructionDrawing constructionDrawing = this.findById(id);
        System.out.println(constructionDrawing.toString());
        if (constructionDrawing.isStatus()) {
            constructionDrawing.setStatus(false);
            this.save(constructionDrawing);
        }
    }
    /**
     *
     * @param pageable
     * @param out_size
     * @param isStaff
     * @return
     */
    public Page<ConstructionDrawing> getAllPageWithCondition(Pageable pageable, int out_size, boolean isStaff, int type,
                                                             String search, String filter){
        try{
            switch (out_size){
                case -1:
                    if(isStaff){
                        switch (type){
                            case 0:
                                return constructionDrawingRepository.findAllWithSearch(pageable, search);
                            case 1:
                                return constructionDrawingRepository.findAllWithFilter(pageable, filter);
                            case 2:
                                return constructionDrawingRepository.findAllWithSearchAndFilter(pageable, search, filter);
                            default:
                                return constructionDrawingRepository.findAll(pageable);

                        }
                    }else{
                        switch(type){
                            case 0:
                                return constructionDrawingRepository.findAllByStatusTrueWithSearch(pageable, search);
                            case 1:
                                return constructionDrawingRepository.findAllByStatusTrueWithFilter(pageable, filter);
                            case 2:
                                return constructionDrawingRepository.findAllByStatusTrueWithSearchAndFilter(pageable, search, filter);
                            default:
                                return constructionDrawingRepository.findAllByStatusTrue(pageable);
                        }

                    }

                case 0:
                    switch(type){
                        case 0:
                            return constructionDrawingRepository.findWithHighestSoldWithSearch(pageable, search);
                        default:
                            return constructionDrawingRepository.findWithHighestSold(pageable);
                    }

            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
