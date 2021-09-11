package com.fa.DPA.DiscountService;

import com.fa.DPA.model.Discount;
import com.fa.DPA.repos.ConstructionDrawingRepository;
import com.fa.DPA.repos.DiscountRepository;
import com.fa.DPA.service.DiscountService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class DiscountServiceTest {
    private DiscountInput input = new DiscountInput();
    private DiscountOutput output = new DiscountOutput();
    private DiscountRepository discountRepositoryMock = Mockito.mock(DiscountRepository.class);
    private ConstructionDrawingRepository constructionDrawingRepositoryMock = Mockito.mock(ConstructionDrawingRepository.class);
    private DiscountService service = new DiscountService(discountRepositoryMock, constructionDrawingRepositoryMock);

    @Test
    public void testGetAllDiscount_UTC01() {
        Pageable input_UTC01 = input.getGetAllDiscount_UTC01();
        Page<Discount> output_UTC01 = output.getGetAllDiscount_UTC01();

        Mockito.when(discountRepositoryMock.findAll(input_UTC01)).thenReturn(output_UTC01);
        Page<Discount> result = service.getAllDiscount(input_UTC01);

        Assert.assertEquals(output_UTC01, result);
    }

    @Test
    public void testGetAllDiscount_UTC02() {
        Pageable input_UTC02 = input.getGetAllDiscount_UTC02();
        Page<Discount> output_UTC02 = output.getGetAllDiscount_UTC02();

        Mockito.when(discountRepositoryMock.findAll(input_UTC02)).thenThrow(NullPointerException.class);
        Page<Discount> result = service.getAllDiscount(input_UTC02);

        Assert.assertEquals(output_UTC02, result);
    }

    @Test
    public void testSaveDiscount_UTC01() {
        Discount input_UTC01 = input.getSaveDiscount_UTC01();
        Discount output_UTC01 = output.getSaveDiscount_UTC01();

        Mockito.when(discountRepositoryMock.findById(input_UTC01.getId())).thenReturn(java.util.Optional.of(input_UTC01));
        Mockito.when(discountRepositoryMock.save(input_UTC01)).thenReturn(output_UTC01);
        Discount result = service.saveDiscount(input_UTC01);

        Assert.assertEquals(output_UTC01, result);
    }

    @Test
    public void testSaveDiscount_UTC02() {
        Discount input_UTC02 = input.getSaveDiscount_UTC02();
        Discount output_UTC02 = output.getSaveDiscount_UTC01();

        Mockito.when(discountRepositoryMock.save(input_UTC02)).thenReturn(output_UTC02);
        Discount result = service.saveDiscount(input_UTC02);

        Assert.assertEquals(output_UTC02, result);
    }

    @Test
    public void testSaveDiscount_UTC03() {
        Discount input_UTC03 = input.getSaveDiscount_UTC03();
        Discount output_UTC03 = output.getSaveDiscount_UTC03();

        Mockito.when(discountRepositoryMock.save(input_UTC03)).thenThrow(NullPointerException.class);
        Discount result = service.saveDiscount(input_UTC03);

        Assert.assertEquals(output_UTC03, result);
    }

    @Test
    public void testSaveDiscount_UTC04() {
        Discount input_UTC04 = input.getSaveDiscount_UTC04();
        Discount output_UTC04 = output.getSaveDiscount_UTC03();

        Mockito.when(discountRepositoryMock.save(input_UTC04)).thenThrow(NullPointerException.class);
        Discount result = service.saveDiscount(input_UTC04);

        Assert.assertEquals(output_UTC04, result);
    }

    @Test
    public void testSaveDiscount_UTC05() {
        Discount input_UTC05 = input.getSaveDiscount_UTC05();
        Discount output_UTC05 = output.getSaveDiscount_UTC03();

        Mockito.when(discountRepositoryMock.save(input_UTC05)).thenThrow(NullPointerException.class);
        Discount result = service.saveDiscount(input_UTC05);

        Assert.assertEquals(output_UTC05, result);
    }

    @Test
    public void testSaveDiscount_UTC06() {
        Discount input_UTC06 = input.getSaveDiscount_UTC06();
        Discount output_UTC06 = output.getSaveDiscount_UTC03();

        Mockito.when(discountRepositoryMock.save(input_UTC06)).thenThrow(NullPointerException.class);
        Discount result = service.saveDiscount(input_UTC06);

        Assert.assertEquals(output_UTC06, result);
    }
}
