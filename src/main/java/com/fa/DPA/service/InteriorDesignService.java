package com.fa.DPA.service;

import com.fa.DPA.controller.output.InteriorOutput;
import com.fa.DPA.converter.InteriorDesignConverter;
import com.fa.DPA.dto.InteriorDesignDTO;
import com.fa.DPA.model.InteriorDesign;
import com.fa.DPA.model.SubCategory;
import com.fa.DPA.repos.InteriorDesignRepository;
import com.fa.DPA.repos.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InteriorDesignService {

    private InteriorDesignRepository interiorDesignRepository;
    private InteriorDesignConverter converter;

    @Autowired
    public InteriorDesignService(InteriorDesignRepository interiorDesignRepository, InteriorDesignConverter converter) {
        this.interiorDesignRepository = interiorDesignRepository;
        this.converter = converter;
    }

    public InteriorDesign save(InteriorDesign interiorDesign){
        try{
            InteriorDesign interiorDesignReturn = new InteriorDesign();
            if(interiorDesign.getId()!=null){
                InteriorDesign oldInteriorDesign = interiorDesignRepository.findById(interiorDesign.getId()).orElse(new InteriorDesign());
                oldInteriorDesign.setTitle(interiorDesign.getTitle());
                oldInteriorDesign.setDescription(interiorDesign.getDescription());
                oldInteriorDesign.setContent(interiorDesign.getContent());
                oldInteriorDesign.setImageSource(interiorDesign.getImageSource());
                oldInteriorDesign.setSubCategories(interiorDesign.getSubCategories());
                oldInteriorDesign.setThumbnail(interiorDesign.getThumbnail());

                interiorDesignReturn = oldInteriorDesign;
            } else{
                interiorDesignReturn = interiorDesign;
            }
            interiorDesignRepository.save(interiorDesignReturn);
            return interiorDesignReturn;
        } catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }

    public void delete(long[] ids){
        for (long items : ids){
            interiorDesignRepository.deleteById(items);
        }
    }

    public Page<InteriorDesign> getALLInteriorPaging(Pageable pageable){
        try{
            return interiorDesignRepository.findAll(pageable);
        } catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }

    public List<InteriorDesignDTO> findAll(Pageable pageable){
        List<InteriorDesignDTO> result = new ArrayList<>();
        List<InteriorDesign> interiorDesigns = interiorDesignRepository.findAll(pageable).getContent();
        for (InteriorDesign items : interiorDesigns){
            InteriorDesignDTO designDTO = converter.toDto(items);
            result.add(designDTO);
        }
        return result;
    }
    public InteriorDesignDTO getInteriorDesignById(Long id){
       try{
         InteriorDesign interiorDesign = interiorDesignRepository.findById(id).orElse(new InteriorDesign());
         return converter.toDto(interiorDesign);
       } catch (Exception exception){
           System.out.println(exception);
       }
       return null;

    }

    public int totalItems(){
        return (int) interiorDesignRepository.count();
    }


}
