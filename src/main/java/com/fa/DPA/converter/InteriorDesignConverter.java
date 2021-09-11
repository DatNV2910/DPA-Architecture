package com.fa.DPA.converter;

import com.fa.DPA.dto.InteriorDesignDTO;
import com.fa.DPA.model.InteriorDesign;

import org.springframework.stereotype.Component;



@Component
public class InteriorDesignConverter {

    public InteriorDesignDTO toDto(InteriorDesign entity){
        InteriorDesignDTO dto = new InteriorDesignDTO();
        if(entity.getId()!= null){
            dto.setId(entity.getId());
        }
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setDescription(entity.getDescription());
        dto.setImageSource(entity.getImageSource());
        dto.setSubCategoryName(entity.getSubCategories().toString());
        dto.setCreateBy(entity.getCreateBy());
        dto.setCreateDate(entity.getCreateDate());
        dto.setThumbnail(entity.getThumbnail());
        return dto;
    }

}
