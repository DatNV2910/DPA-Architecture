package com.fa.DPA.SubcategoryService;

import com.fa.DPA.converter.SubcategoryConverter;
import com.fa.DPA.dto.SubcategoryDTO;
import com.fa.DPA.model.SubCategory;
import com.fa.DPA.repos.SubCategoryRepository;
import com.fa.DPA.service.SubcategoryService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class SubcategoryServiceTest {
    private SubcategoryInput input = new SubcategoryInput();
    private SubcategoryOutput output = new SubcategoryOutput();
    private SubCategoryRepository subCategoryRepositoryMock = Mockito.mock(SubCategoryRepository.class);
    private SubcategoryConverter converter = new SubcategoryConverter();
    private SubcategoryService service = new SubcategoryService(subCategoryRepositoryMock, converter);

    @Test
    public void testFindAll_UTC01() {
        Pageable input_UTC01 = input.getFindAll_UTC01();
        List<SubcategoryDTO> output_UTC01 = output.getFindAll_UTC01();

        List<SubCategory> subCategoryList = new ArrayList<>();
        subCategoryList.add(output.getSaveSubcategory_UTC01());
        Page<SubCategory> subCategoryPage = new PageImpl<>(subCategoryList);
        Mockito.when(subCategoryRepositoryMock.findAll(input_UTC01)).thenReturn(subCategoryPage);
        List<SubcategoryDTO> result = service.findAll(input_UTC01);

        Assert.assertEquals(output_UTC01, result);
    }

    @Test
    public void testGetAllSubcategoryByCategory_UTC01() {
        Pageable pageable_input_UTC01 = input.getPageable_getAllSubcategoryByCategory_UTC01();
        Long categoryId_input_UTC01 = input.getCategoryId_getAllSubcategoryByCategory_UTC01();
        List<SubcategoryDTO> output_UTC01 = output.getGetAllSubcategoryByCategory_UTC01();

        List<SubCategory> subCategoryList = new ArrayList<>();
        subCategoryList.add(output.getSaveSubcategory_UTC01());
        Page<SubCategory> subCategoryPage = new PageImpl<>(subCategoryList);
        Mockito.when(subCategoryRepositoryMock.findByCategory(categoryId_input_UTC01, pageable_input_UTC01)).thenReturn(subCategoryPage);
        List<SubcategoryDTO> result = service.getAllSubcategoryByCategory(pageable_input_UTC01, categoryId_input_UTC01);

        Assert.assertEquals(output_UTC01, result);
    }

    @Test
    public void testGetAllSubcategoryByCategory_UTC02() {
        Pageable pageable_input_UTC02 = input.getPageable_getAllSubcategoryByCategory_UTC02();
        Long categoryId_input_UTC02 = input.getCategoryId_getAllSubcategoryByCategory_UTC02();
        List<SubcategoryDTO> output_UTC02 = output.getGetAllSubcategoryByCategory_UTC02();

        Mockito.when(subCategoryRepositoryMock.findByCategory(categoryId_input_UTC02, pageable_input_UTC02)).thenThrow(EntityNotFoundException.class);
        List<SubcategoryDTO> result = service.getAllSubcategoryByCategory(pageable_input_UTC02, categoryId_input_UTC02);

        Assert.assertEquals(output_UTC02, result);
    }

    @Test
    public void testGetAllSubcategoryByCategory_UTC03() {
        Pageable pageable_input_UTC03 = input.getPageable_getAllSubcategoryByCategory_UTC03();
        Long categoryId_input_UTC03 = input.getCategoryId_getAllSubcategoryByCategory_UTC03();
        List<SubcategoryDTO> output_UTC03 = output.getGetAllSubcategoryByCategory_UTC02();

        Mockito.when(subCategoryRepositoryMock.findByCategory(categoryId_input_UTC03, pageable_input_UTC03)).thenThrow(NullPointerException.class);
        List<SubcategoryDTO> result = service.getAllSubcategoryByCategory(pageable_input_UTC03, categoryId_input_UTC03);

        Assert.assertEquals(output_UTC03, result);
    }

    @Test
    public void testGetAllSubcategoryByCategory_UTC04() {
        Pageable pageable_input_UTC04 = input.getPageable_getAllSubcategoryByCategory_UTC04();
        Long categoryId_input_UTC04 = input.getCategoryId_getAllSubcategoryByCategory_UTC04();
        List<SubcategoryDTO> output_UTC04 = output.getGetAllSubcategoryByCategory_UTC02();

        Mockito.when(subCategoryRepositoryMock.findByCategory(categoryId_input_UTC04, pageable_input_UTC04)).thenThrow(NullPointerException.class);
        List<SubcategoryDTO> result = service.getAllSubcategoryByCategory(pageable_input_UTC04, categoryId_input_UTC04);

        Assert.assertEquals(output_UTC04, result);
    }

    @Test
    public void testSaveSubcategory_UTC01() {
        SubCategory input_UTC01 = input.getSaveSubcategory_UTC01();
        SubCategory output_UTC01 = output.getSaveSubcategory_UTC01();

        Mockito.when(subCategoryRepositoryMock.findById(input_UTC01.getId())).thenReturn(java.util.Optional.of(input_UTC01));
        Mockito.when(subCategoryRepositoryMock.save(input_UTC01)).thenReturn(output_UTC01);
        SubCategory result = service.saveSubcategory(input_UTC01);

        Assert.assertEquals(output_UTC01, result);
    }

    @Test
    public void testSaveSubcategory_UTC02() {
        SubCategory input_UTC02 = input.getSaveSubcategory_UTC02();
        SubCategory output_UTC02 = output.getSaveSubcategory_UTC02();

        Mockito.when(subCategoryRepositoryMock.save(input_UTC02)).thenReturn(output_UTC02);
        SubCategory result = service.saveSubcategory(input_UTC02);

        Assert.assertEquals(output_UTC02, result);
    }

    @Test
    public void testSaveSubcategory_UTC03() {
        SubCategory input_UTC03 = input.getSaveSubcategory_UTC03();
        SubCategory output_UTC03 = output.getSaveSubcategory_UTC03();

        Mockito.when(subCategoryRepositoryMock.save(input_UTC03)).thenReturn(output_UTC03);
        SubCategory result = service.saveSubcategory(input_UTC03);

        Assert.assertEquals(output_UTC03, result);
    }

    @Test
    public void testSaveSubcategory_UTC04() {
        SubCategory input_UTC04 = input.getSaveSubcategory_UTC04();
        SubCategory output_UTC04 = output.getSaveSubcategory_UTC04();

        Mockito.when(subCategoryRepositoryMock.save(input_UTC04)).thenThrow(NullPointerException.class);
        SubCategory result = service.saveSubcategory(input_UTC04);

        Assert.assertEquals(output_UTC04, result);
    }

    @Test
    public void testSaveSubcategory_UTC05() {
        SubCategory input_UTC05 = input.getSaveSubcategory_UTC05();
        SubCategory output_UTC05 = output.getSaveSubcategory_UTC04();

        Mockito.when(subCategoryRepositoryMock.save(input_UTC05)).thenThrow(NullPointerException.class);
        SubCategory result = service.saveSubcategory(input_UTC05);

        Assert.assertEquals(output_UTC05, result);
    }

    @Test
    public void testSaveSubcategory_UTC06() {
        SubCategory input_UTC06 = input.getSaveSubcategory_UTC06();
        SubCategory output_UTC06 = output.getSaveSubcategory_UTC04();

        Mockito.when(subCategoryRepositoryMock.save(input_UTC06)).thenThrow(NullPointerException.class);
        SubCategory result = service.saveSubcategory(input_UTC06);

        Assert.assertEquals(output_UTC06, result);
    }

    @Test
    public void testSaveSubcategory_UTC07() {
        SubCategory input_UTC07 = input.getSaveSubcategory_UTC07();
        SubCategory output_UTC07 = output.getSaveSubcategory_UTC04();

        Mockito.when(subCategoryRepositoryMock.save(input_UTC07)).thenThrow(NullPointerException.class);
        SubCategory result = service.saveSubcategory(input_UTC07);

        Assert.assertEquals(output_UTC07, result);
    }

    @Test
    public void testSaveSubcategory_UTC08() {
        SubCategory input_UTC08 = input.getSaveSubcategory_UTC08();
        SubCategory output_UTC08 = output.getSaveSubcategory_UTC04();

        Mockito.when(subCategoryRepositoryMock.save(input_UTC08)).thenThrow(NullPointerException.class);
        SubCategory result = service.saveSubcategory(input_UTC08);

        Assert.assertEquals(output_UTC08, result);
    }
}
