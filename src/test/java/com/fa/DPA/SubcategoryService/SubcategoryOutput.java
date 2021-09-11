package com.fa.DPA.SubcategoryService;

import com.fa.DPA.converter.SubcategoryConverter;
import com.fa.DPA.dto.SubcategoryDTO;
import com.fa.DPA.model.Category;
import com.fa.DPA.model.InteriorDesign;
import com.fa.DPA.model.SubCategory;

import java.util.ArrayList;
import java.util.List;

public class SubcategoryOutput {
    private SubcategoryConverter converter = new SubcategoryConverter();
    private SubCategory saveSubcategory_UTC01;
    private SubCategory saveSubcategory_UTC02;
    private SubCategory saveSubcategory_UTC03;
    private SubCategory saveSubcategory_UTC04;
    private List<SubcategoryDTO> findAll_UTC01;
    private List<SubcategoryDTO> findAll_UTC02;
    private List<SubcategoryDTO> getAllSubcategoryByCategory_UTC01;
    private List<SubcategoryDTO> getAllSubcategoryByCategory_UTC02;

    public SubcategoryOutput() {
        List<InteriorDesign> interiorDesignList = new ArrayList<>();
        interiorDesignList.add(new InteriorDesign());
        Category category = new Category();
        category.setId((long) 1);
        category.setName("This category name");
        saveSubcategory_UTC01 = new SubCategory();
        saveSubcategory_UTC01.setId((long) 1);
        saveSubcategory_UTC01.setStatus(true);
        saveSubcategory_UTC01.setCategory(category);
        saveSubcategory_UTC01.setName("This subcategory name");
        saveSubcategory_UTC01.setInteriorDesigns(interiorDesignList);

        saveSubcategory_UTC02 = new SubCategory();
        saveSubcategory_UTC02.setStatus(true);
        saveSubcategory_UTC02.setCategory(category);
        saveSubcategory_UTC02.setName("This subcategory name");
        saveSubcategory_UTC02.setInteriorDesigns(interiorDesignList);

        saveSubcategory_UTC03 = new SubCategory();
        saveSubcategory_UTC03.setStatus(false);
        saveSubcategory_UTC03.setCategory(category);
        saveSubcategory_UTC03.setName("This subcategory name");
        saveSubcategory_UTC03.setInteriorDesigns(interiorDesignList);

        saveSubcategory_UTC04 = null;

        SubcategoryDTO subcategoryDTO = converter.toDto(saveSubcategory_UTC01);
        findAll_UTC01 = new ArrayList<>();
        findAll_UTC01.add(subcategoryDTO);
        findAll_UTC02 = null;

        getAllSubcategoryByCategory_UTC01 = new ArrayList<>();
        getAllSubcategoryByCategory_UTC01.add(subcategoryDTO);
        getAllSubcategoryByCategory_UTC02 = null;
    }

    public SubCategory getSaveSubcategory_UTC01() {
        return saveSubcategory_UTC01;
    }

    public SubCategory getSaveSubcategory_UTC02() {
        return saveSubcategory_UTC02;
    }

    public SubCategory getSaveSubcategory_UTC03() {
        return saveSubcategory_UTC03;
    }

    public SubCategory getSaveSubcategory_UTC04() {
        return saveSubcategory_UTC04;
    }

    public List<SubcategoryDTO> getFindAll_UTC01() {
        return findAll_UTC01;
    }

    public List<SubcategoryDTO> getFindAll_UTC02() {
        return findAll_UTC02;
    }

    public List<SubcategoryDTO> getGetAllSubcategoryByCategory_UTC01() {
        return getAllSubcategoryByCategory_UTC01;
    }

    public List<SubcategoryDTO> getGetAllSubcategoryByCategory_UTC02() {
        return getAllSubcategoryByCategory_UTC02;
    }
}
