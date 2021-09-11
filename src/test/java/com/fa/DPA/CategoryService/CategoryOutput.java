package com.fa.DPA.CategoryService;

import com.fa.DPA.model.Category;
import com.fa.DPA.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

public class CategoryOutput {
    private Page<Category> getAllCategory_UTC01;

    private Page<Category> getAllCategory_UTC02;

    private Page<Category> getAllCategoryByInteriorDesign_UTC01;

    private Page<Category> getAllCategoryByInteriorDesign_UTC02;

    private Page<Category> getAllCategoryByConstruction_UTC01;

    private Page<Category> getAllCategoryByConstruction_UTC02;

    private Category saveCategory_UTC01;

    private Category saveCategory_UTC02;

    private Category saveCategory_UTC03;

    private Category saveCategory_UTC07;

    public CategoryOutput() {
        List<Category> categoryList = new ArrayList<>();
        getAllCategory_UTC01 = new PageImpl<>(categoryList);

        getAllCategory_UTC02 = null;

        getAllCategoryByConstruction_UTC01 = new PageImpl<>(categoryList);

        getAllCategoryByConstruction_UTC02 = null;

        getAllCategoryByInteriorDesign_UTC01 = new PageImpl<>(categoryList);

        getAllCategoryByInteriorDesign_UTC02 = null;

        Type type = new Type();
        type.setId((long) 1);

        saveCategory_UTC01 = new Category();
        saveCategory_UTC01.setId((long) 2);
        saveCategory_UTC01.setName("Category test 01");
        saveCategory_UTC01.setStatus(false);
        saveCategory_UTC01.setType(type);

        saveCategory_UTC02 = new Category();
        saveCategory_UTC02.setId((long) 1);
        saveCategory_UTC02.setName("Category test 02");
        saveCategory_UTC02.setStatus(false);
        saveCategory_UTC02.setType(type);

        saveCategory_UTC03 = null;

        saveCategory_UTC07 = new Category();
        saveCategory_UTC07.setId((long) 1);
        saveCategory_UTC07.setName("Biet thu");
        saveCategory_UTC07.setStatus(true);
        saveCategory_UTC07.setType(type);
    }

    public Page<Category> getGetAllCategory_UTC01() {
        return getAllCategory_UTC01;
    }

    public Page<Category> getGetAllCategory_UTC02() {
        return getAllCategory_UTC02;
    }

    public Category getSaveCategory_UTC01() {
        return saveCategory_UTC01;
    }

    public Category getSaveCategory_UTC02() {
        return saveCategory_UTC02;
    }

    public Category getSaveCategory_UTC03() {
        return saveCategory_UTC03;
    }

    public Category getSaveCategory_UTC07() {
        return saveCategory_UTC07;
    }

    public Page<Category> getGetAllCategoryByInteriorDesign_UTC01() {
        return getAllCategoryByInteriorDesign_UTC01;
    }

    public Page<Category> getGetAllCategoryByInteriorDesign_UTC02() {
        return getAllCategoryByInteriorDesign_UTC02;
    }

    public Page<Category> getGetAllCategoryByConstruction_UTC01() {
        return getAllCategoryByConstruction_UTC01;
    }

    public Page<Category> getGetAllCategoryByConstruction_UTC02() {
        return getAllCategoryByConstruction_UTC02;
    }
}
