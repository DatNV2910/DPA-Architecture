package com.fa.DPA.service;


import com.fa.DPA.converter.SubcategoryConverter;
import com.fa.DPA.dto.SubcategoryDTO;
import com.fa.DPA.model.CartItem;
import com.fa.DPA.model.SubCategory;
import com.fa.DPA.repos.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SubcategoryService {
    private SubCategoryRepository subCategoryRepository;

    private SubcategoryConverter converter;

    @Autowired
    public SubcategoryService(SubCategoryRepository subCategoryRepository, SubcategoryConverter converter) {
        this.subCategoryRepository = subCategoryRepository;
        this.converter = converter;
    }

    public List<SubcategoryDTO> findAll(Pageable pageable){
        try {
            List<SubcategoryDTO> result = new ArrayList<>();
            List<SubCategory> subCategories = subCategoryRepository.findAll(pageable).getContent();
            for (SubCategory items : subCategories) {
                SubcategoryDTO dto = converter.toDto(items);
                result.add(dto);
            }
            return result;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    public List<SubcategoryDTO> getAllSubcategoryByCategory(Pageable pageable, long category_id){
        try {
            List<SubcategoryDTO> result = new ArrayList<>();
            List<SubCategory> subCategories = subCategoryRepository.findByCategory(category_id, pageable).getContent();
            for (SubCategory items : subCategories){
                SubcategoryDTO dto = converter.toDto(items);
                result.add(dto);
            }
            return result;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    public int totalItems(){
        return (int) subCategoryRepository.count();
    }




    public SubCategory saveSubcategory(SubCategory subCategory){
        try{
            SubCategory subCategoryReturn = new SubCategory();
            if(subCategory.getId() != null){
                SubCategory oldSubcateogy = subCategoryRepository.findById(subCategory.getId()).orElse(new SubCategory());
                oldSubcateogy.setName(subCategory.getName());
                oldSubcateogy.setStatus(subCategory.isStatus());
                oldSubcateogy.setCategory(subCategory.getCategory());
                subCategoryReturn = oldSubcateogy;
            } else {
                subCategoryReturn = subCategory;
            }
            subCategoryReturn = subCategoryRepository.save(subCategoryReturn);
            return subCategoryReturn;
        } catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }



}
