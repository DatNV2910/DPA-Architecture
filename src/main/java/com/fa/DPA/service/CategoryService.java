package com.fa.DPA.service;

import com.fa.DPA.model.Category;
import com.fa.DPA.repos.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Page<Category> getAllCategory(Pageable pageable){
        try{
            return categoryRepository.findAll(pageable);
        } catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }
    public Page<Category> getAllCategoryByInteriorDesign(Pageable pageable){
        try{
            return categoryRepository.findWithInteriorDesign(pageable);
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public Page<Category> getAllCategoryByConstruction(Pageable pageable){
        try{
            return categoryRepository.findWithConstruction(pageable);
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public Category saveCategory(Category category){
        try {
            Category categoryReturn = new Category();
            if(category.getId() != null){
                Category oldCategory = categoryRepository.findById(category.getId()).orElse(new Category());
                oldCategory.setName(category.getName());
                oldCategory.setStatus(category.isStatus());
                oldCategory.setType(category.getType());
                categoryReturn = oldCategory;
            }
            else {
                categoryReturn = category;
            }
            categoryReturn = categoryRepository.save(categoryReturn);
            return categoryReturn;
        } catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }
}
