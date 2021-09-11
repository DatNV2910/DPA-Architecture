package com.fa.DPA.CategoryService;

import com.fa.DPA.model.Category;
import com.fa.DPA.repos.CategoryRepository;
import com.fa.DPA.service.CategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CategoryServiceTest {
    private CategoryInput input = new CategoryInput();
    private CategoryOutput output = new CategoryOutput();
    CategoryRepository categoryRepositoryMock = Mockito.mock(CategoryRepository.class);
    CategoryService categoryService = new CategoryService(categoryRepositoryMock);


    @Test
    public void testGetAllCategory_UTC01() {
        CategoryRepository categoryRepositoryMock = Mockito.mock(CategoryRepository.class);
        CategoryService categoryService = new CategoryService(categoryRepositoryMock);

        Pageable input_UTC01 = input.getGetAllCategory_UTC01();
        Page<Category> output_UTC01 = output.getGetAllCategory_UTC01();

        Mockito.when(categoryRepositoryMock.findAll(input_UTC01)).thenReturn(output_UTC01);
        Page<Category> result = categoryService.getAllCategory(input_UTC01);

        Assert.assertEquals(result, output_UTC01);
    }

    @Test
    public void testGetAllCategory_UTC02() {

        Pageable input_UTC02 = input.getGetAllCategory_UTC02();
        Page<Category> output_UTC02 = output.getGetAllCategory_UTC02();

        Mockito.when(categoryRepositoryMock.findAll(input_UTC02)).thenThrow(NullPointerException.class);
        Page<Category> result = categoryService.getAllCategory(input_UTC02);

        Assert.assertEquals(result, output_UTC02);
    }

    @Test
    public void testGetAllCategoryByConstruction_UTC01() {
        CategoryRepository categoryRepositoryMock = Mockito.mock(CategoryRepository.class);
        CategoryService categoryService = new CategoryService(categoryRepositoryMock);

        Pageable input_UTC01 = input.getGetAllCategoryByConstruction_UTC01();
        Page<Category> output_UTC01 = output.getGetAllCategoryByConstruction_UTC01();

        Mockito.when(categoryRepositoryMock.findWithConstruction(input_UTC01)).thenReturn(output_UTC01);
        Page<Category> result = categoryService.getAllCategoryByConstruction(input_UTC01);

        Assert.assertEquals(result, output_UTC01);
    }

    @Test
    public void testGetAllCategoryByConstruction_UTC02() {

        Pageable input_UTC02 = input.getGetAllCategoryByConstruction_UTC02();
        Page<Category> output_UTC02 = output.getGetAllCategoryByConstruction_UTC02();

        Mockito.when(categoryRepositoryMock.findWithConstruction(input_UTC02)).thenThrow(NullPointerException.class);
        Page<Category> result = categoryService.getAllCategoryByConstruction(input_UTC02);

        Assert.assertEquals(result, output_UTC02);
    }

    @Test
    public void testGetAllCategoryByInteriorDesign_UTC01() {
        CategoryRepository categoryRepositoryMock = Mockito.mock(CategoryRepository.class);
        CategoryService categoryService = new CategoryService(categoryRepositoryMock);

        Pageable input_UTC01 = input.getGetAllCategoryByInteriorDesign_UTC01();
        Page<Category> output_UTC01 = output.getGetAllCategoryByInteriorDesign_UTC01();

        Mockito.when(categoryRepositoryMock.findWithInteriorDesign(input_UTC01)).thenReturn(output_UTC01);
        Page<Category> result = categoryService.getAllCategoryByInteriorDesign(input_UTC01);

        Assert.assertEquals(result, output_UTC01);
    }

    @Test
    public void testGetAllCategoryByInteriorDesign_UTC02() {

        Pageable input_UTC02 = input.getGetAllCategoryByInteriorDesign_UTC02();
        Page<Category> output_UTC02 = output.getGetAllCategoryByInteriorDesign_UTC02();

        Mockito.when(categoryRepositoryMock.findWithInteriorDesign(input_UTC02)).thenThrow(NullPointerException.class);
        Page<Category> result = categoryService.getAllCategoryByInteriorDesign(input_UTC02);

        Assert.assertEquals(result, output_UTC02);
    }

    @Test
    public void testSaveCategory_UTC01() {

        Category input_UTC01 = input.getSaveCategory_UTC01();
        Category output_UTC01 = output.getSaveCategory_UTC01();

        Mockito.when(categoryRepositoryMock.save(input_UTC01)).thenReturn(output_UTC01);
        Category result = categoryService.saveCategory(input_UTC01);

        Assert.assertEquals(result, output_UTC01);
    }

    @Test
    public void testSaveCategory_UTC02() {

        Category input_UTC02 = input.getSaveCategory_UTC02();
        Category output_UTC02 = output.getSaveCategory_UTC02();

        Mockito.when(categoryRepositoryMock.save(input_UTC02)).thenReturn(output_UTC02);
        Category result = categoryService.saveCategory(input_UTC02);

        Assert.assertEquals(result, output_UTC02);
    }

    @Test
    public void testSaveCategory_UTC03() {

        Category input_UTC03 = input.getSaveCategory_UTC03();
        Category output_UTC03 = output.getSaveCategory_UTC03();

        Mockito.when(categoryRepositoryMock.save(input_UTC03)).thenThrow(NullPointerException.class);
        Category result = categoryService.saveCategory(input_UTC03);

        Assert.assertEquals(result, output_UTC03);
    }

    @Test
    public void testSaveCategory_UTC04() {

        Category input_UTC04 = input.getSaveCategory_UTC04();
        Category output_UTC04 = output.getSaveCategory_UTC03();

        Mockito.when(categoryRepositoryMock.save(input_UTC04)).thenThrow(NullPointerException.class);
        Category result = categoryService.saveCategory(input_UTC04);

        Assert.assertEquals(result, output_UTC04);
    }

    @Test
    public void testSaveCategory_UTC05() {

        Category input_UTC05 = input.getSaveCategory_UTC05();
        Category output_UTC05 = output.getSaveCategory_UTC03();

        Mockito.when(categoryRepositoryMock.save(input_UTC05)).thenThrow(NullPointerException.class);
        Category result = categoryService.saveCategory(input_UTC05);

        Assert.assertEquals(result, output_UTC05);
    }

    @Test
    public void testSaveCategory_UTC06() {

        Category input_UTC06 = input.getSaveCategory_UTC06();
        Category output_UTC06 = output.getSaveCategory_UTC03();

        Mockito.when(categoryRepositoryMock.save(input_UTC06)).thenThrow(NullPointerException.class);
        Category result = categoryService.saveCategory(input_UTC06);

        Assert.assertEquals(result, output_UTC06);
    }

    @Test
    public void testSaveCategory_UTC07() {

        Category input_UTC07 = input.getSaveCategory_UTC07();
        Category output_UTC07 = output.getSaveCategory_UTC07();

        Mockito.when(categoryRepositoryMock.save(input_UTC07)).thenReturn(output_UTC07);
        Mockito.when(categoryRepositoryMock.findById(input_UTC07.getId())).thenReturn(java.util.Optional.of(input_UTC07));
        Category result = categoryService.saveCategory(input_UTC07);

        Assert.assertEquals(result, output_UTC07);
    }
}
