package com.fa.DPA.CategoryService;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.model.Category;
import com.fa.DPA.model.Type;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class CategoryInput {
    private Pageable getAllCategory_UTC01;

    private Pageable getAllCategory_UTC02;

    private Pageable getAllCategoryByInteriorDesign_UTC01;

    private Pageable getAllCategoryByInteriorDesign_UTC02;

    private Pageable getAllCategoryByConstruction_UTC01;

    private Pageable getAllCategoryByConstruction_UTC02;

    private Category saveCategory_UTC01;

    private Category saveCategory_UTC02;

    private Category saveCategory_UTC03;

    private Category saveCategory_UTC04;

    private Category saveCategory_UTC05;

    private Category saveCategory_UTC06;

    private Category saveCategory_UTC07;

    public CategoryInput() {
        getAllCategory_UTC01 = PageRequest.of(0, Constant.DEFAULT_PAGE_SIZE);

        getAllCategory_UTC02 = null;

        getAllCategoryByInteriorDesign_UTC01 = PageRequest.of(0, Constant.DEFAULT_PAGE_SIZE);

        getAllCategoryByInteriorDesign_UTC02 = null;

        getAllCategoryByConstruction_UTC01 = PageRequest.of(0, Constant.DEFAULT_PAGE_SIZE);

        getAllCategoryByConstruction_UTC02 = null;

        Type type = new Type();
        type.setId((long) 1);

        saveCategory_UTC01 = new Category();
        saveCategory_UTC01.setName("Biet thu");
        saveCategory_UTC01.setStatus(true);
        saveCategory_UTC01.setType(type);

        saveCategory_UTC02 = new Category();
        saveCategory_UTC02.setName("Biet thu");
        saveCategory_UTC02.setStatus(false);
        saveCategory_UTC02.setType(type);

        saveCategory_UTC03 = new Category();
        saveCategory_UTC03.setStatus(true);
        saveCategory_UTC03.setType(type);

        saveCategory_UTC04 = new Category();
        saveCategory_UTC04.setName("Biet thu");
        saveCategory_UTC04.setType(type);

        saveCategory_UTC05 = new Category();
        saveCategory_UTC05.setName("Biet thu");
        saveCategory_UTC05.setStatus(false);

        saveCategory_UTC06 = null;

        saveCategory_UTC07 = new Category();
        saveCategory_UTC07.setId((long) 1);
        saveCategory_UTC07.setName("Biet thu");
        saveCategory_UTC07.setStatus(true);
        saveCategory_UTC07.setType(type);
    }

    public Pageable getGetAllCategory_UTC01() {
        return getAllCategory_UTC01;
    }

    public Pageable getGetAllCategory_UTC02() {
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

    public Category getSaveCategory_UTC04() {
        return saveCategory_UTC04;
    }

    public Category getSaveCategory_UTC05() {
        return saveCategory_UTC05;
    }

    public Category getSaveCategory_UTC06() {
        return saveCategory_UTC06;
    }

    public Category getSaveCategory_UTC07() {
        return saveCategory_UTC07;
    }

    public Pageable getGetAllCategoryByInteriorDesign_UTC01() {
        return getAllCategoryByInteriorDesign_UTC01;
    }

    public Pageable getGetAllCategoryByInteriorDesign_UTC02() {
        return getAllCategoryByInteriorDesign_UTC02;
    }

    public Pageable getGetAllCategoryByConstruction_UTC01() {
        return getAllCategoryByConstruction_UTC01;
    }

    public Pageable getGetAllCategoryByConstruction_UTC02() {
        return getAllCategoryByConstruction_UTC02;
    }
}
