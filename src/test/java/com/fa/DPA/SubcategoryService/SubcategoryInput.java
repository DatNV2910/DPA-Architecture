package com.fa.DPA.SubcategoryService;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.model.Category;
import com.fa.DPA.model.InteriorDesign;
import com.fa.DPA.model.SubCategory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class SubcategoryInput {
    private Pageable findAll_UTC01;
    private Pageable findAll_UTC02;
    private Pageable pageable_getAllSubcategoryByCategory_UTC01;
    private Pageable pageable_getAllSubcategoryByCategory_UTC02;
    private Pageable pageable_getAllSubcategoryByCategory_UTC03;
    private Pageable pageable_getAllSubcategoryByCategory_UTC04;
    private Long categoryId_getAllSubcategoryByCategory_UTC01;
    private Long categoryId_getAllSubcategoryByCategory_UTC02;
    private Long categoryId_getAllSubcategoryByCategory_UTC03;
    private Long categoryId_getAllSubcategoryByCategory_UTC04;
    private SubCategory saveSubcategory_UTC01;
    private SubCategory saveSubcategory_UTC02;
    private SubCategory saveSubcategory_UTC03;
    private SubCategory saveSubcategory_UTC04;
    private SubCategory saveSubcategory_UTC05;
    private SubCategory saveSubcategory_UTC06;
    private SubCategory saveSubcategory_UTC07;
    private SubCategory saveSubcategory_UTC08;

    public SubcategoryInput() {
        findAll_UTC01 = PageRequest.of(0, Constant.DEFAULT_PAGE_SIZE);
        findAll_UTC02 = null;

        pageable_getAllSubcategoryByCategory_UTC01 = PageRequest.of(0, Constant.DEFAULT_PAGE_SIZE);
        pageable_getAllSubcategoryByCategory_UTC02 = PageRequest.of(0, Constant.DEFAULT_PAGE_SIZE);
        pageable_getAllSubcategoryByCategory_UTC03 = null;
        pageable_getAllSubcategoryByCategory_UTC04 = null;
        categoryId_getAllSubcategoryByCategory_UTC01 = (long) 1;
        categoryId_getAllSubcategoryByCategory_UTC02 = (long) -1;
        categoryId_getAllSubcategoryByCategory_UTC03 = (long) 1;
        categoryId_getAllSubcategoryByCategory_UTC04 = null;

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

        saveSubcategory_UTC04 = new SubCategory();
        saveSubcategory_UTC04.setCategory(category);
        saveSubcategory_UTC04.setName("This subcategory name");
        saveSubcategory_UTC04.setInteriorDesigns(interiorDesignList);

        saveSubcategory_UTC05 = new SubCategory();
        saveSubcategory_UTC05.setStatus(true);
        saveSubcategory_UTC05.setName("This subcategory name");
        saveSubcategory_UTC05.setInteriorDesigns(interiorDesignList);

        saveSubcategory_UTC06 = new SubCategory();
        saveSubcategory_UTC06.setStatus(true);
        saveSubcategory_UTC06.setCategory(category);
        saveSubcategory_UTC06.setInteriorDesigns(interiorDesignList);

        saveSubcategory_UTC07 = new SubCategory();
        saveSubcategory_UTC07.setStatus(true);
        saveSubcategory_UTC07.setCategory(category);
        saveSubcategory_UTC07.setName("This subcategory name");

        saveSubcategory_UTC08 = null;
    }

    public Pageable getFindAll_UTC01() {
        return findAll_UTC01;
    }

    public Pageable getFindAll_UTC02() {
        return findAll_UTC02;
    }

    public Pageable getPageable_getAllSubcategoryByCategory_UTC01() {
        return pageable_getAllSubcategoryByCategory_UTC01;
    }

    public Pageable getPageable_getAllSubcategoryByCategory_UTC02() {
        return pageable_getAllSubcategoryByCategory_UTC02;
    }

    public Pageable getPageable_getAllSubcategoryByCategory_UTC03() {
        return pageable_getAllSubcategoryByCategory_UTC03;
    }

    public Pageable getPageable_getAllSubcategoryByCategory_UTC04() {
        return pageable_getAllSubcategoryByCategory_UTC04;
    }

    public Long getCategoryId_getAllSubcategoryByCategory_UTC01() {
        return categoryId_getAllSubcategoryByCategory_UTC01;
    }

    public Long getCategoryId_getAllSubcategoryByCategory_UTC02() {
        return categoryId_getAllSubcategoryByCategory_UTC02;
    }

    public Long getCategoryId_getAllSubcategoryByCategory_UTC03() {
        return categoryId_getAllSubcategoryByCategory_UTC03;
    }

    public Long getCategoryId_getAllSubcategoryByCategory_UTC04() {
        return categoryId_getAllSubcategoryByCategory_UTC04;
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

    public SubCategory getSaveSubcategory_UTC05() {
        return saveSubcategory_UTC05;
    }

    public SubCategory getSaveSubcategory_UTC06() {
        return saveSubcategory_UTC06;
    }

    public SubCategory getSaveSubcategory_UTC07() {
        return saveSubcategory_UTC07;
    }

    public SubCategory getSaveSubcategory_UTC08() {
        return saveSubcategory_UTC08;
    }
}
