package com.fa.DPA.CdOrderService;

import com.fa.DPA.model.CdOrder;
import com.fa.DPA.repos.CdOrderRepository;
import com.fa.DPA.service.CdOrderService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class CdOrderServiceTest {
    private CdOrderInput input = new CdOrderInput();
    private CdOrderOutput output = new CdOrderOutput();
    private CdOrderService service;

    @Test
    public void testSave_UTC01() {
        CdOrderRepository cdOrderRepositoryMock = Mockito.mock(CdOrderRepository.class);
        service = new CdOrderService(cdOrderRepositoryMock);

        CdOrder input_UTC01 = input.getSave_UTC01();
        CdOrder output_UTC01 = output.getSave_UTC01();

        Mockito.when(cdOrderRepositoryMock.save(input_UTC01)).thenReturn(output_UTC01);
        CdOrder result = service.save(input_UTC01);

        Assert.assertEquals(output_UTC01, result);
    }

    @Test
    public void testSave_UTC02() {
        CdOrderRepository cdOrderRepositoryMock = Mockito.mock(CdOrderRepository.class);
        service = new CdOrderService(cdOrderRepositoryMock);

        CdOrder input_UTC02 = input.getSave_UTC02();
        CdOrder output_UTC02 = output.getSave_UTC02();

        Mockito.when(cdOrderRepositoryMock.save(input_UTC02)).thenThrow(NullPointerException.class);
        CdOrder result = service.save(input_UTC02);

        Assert.assertEquals(output_UTC02, result);
    }

    @Test
    public void testSave_UTC03() {
        CdOrderRepository cdOrderRepositoryMock = Mockito.mock(CdOrderRepository.class);
        service = new CdOrderService(cdOrderRepositoryMock);

        CdOrder input_UTC03 = input.getSave_UTC03();
        CdOrder output_UTC03 = output.getSave_UTC03();

        Mockito.when(cdOrderRepositoryMock.save(input_UTC03)).thenThrow(NullPointerException.class);
        CdOrder result = service.save(input_UTC03);

        Assert.assertEquals(output_UTC03, result);
    }

    @Test
    public void testSave_UTC04() {
        CdOrderRepository cdOrderRepositoryMock = Mockito.mock(CdOrderRepository.class);
        service = new CdOrderService(cdOrderRepositoryMock);

        CdOrder input_UTC04 = input.getSave_UTC04();
        CdOrder output_UTC04 = output.getSave_UTC04();

        Mockito.when(cdOrderRepositoryMock.save(input_UTC04)).thenThrow(NullPointerException.class);
        CdOrder result = service.save(input_UTC04);

        Assert.assertEquals(output_UTC04, result);
    }

    @Test
    public void testSave_UTC05() {
        CdOrderRepository cdOrderRepositoryMock = Mockito.mock(CdOrderRepository.class);
        service = new CdOrderService(cdOrderRepositoryMock);

        CdOrder input_UTC05 = input.getSave_UTC05();
        CdOrder output_UTC05 = output.getSave_UTC05();

        Mockito.when(cdOrderRepositoryMock.save(input_UTC05)).thenThrow(NullPointerException.class);
        CdOrder result = service.save(input_UTC05);

        Assert.assertEquals(output_UTC05, result);
    }

    @Test
    public void testSave_UTC06() {
        CdOrderRepository cdOrderRepositoryMock = Mockito.mock(CdOrderRepository.class);
        service = new CdOrderService(cdOrderRepositoryMock);

        CdOrder input_UTC06 = input.getSave_UTC06();
        CdOrder output_UTC06 = output.getSave_UTC06();

        Mockito.when(cdOrderRepositoryMock.save(input_UTC06)).thenThrow(NullPointerException.class);
        CdOrder result = service.save(input_UTC06);

        Assert.assertEquals(output_UTC06, result);
    }

    @Test
    public void testSave_UTC07() {
        CdOrderRepository cdOrderRepositoryMock = Mockito.mock(CdOrderRepository.class);
        service = new CdOrderService(cdOrderRepositoryMock);

        CdOrder input_UTC07 = input.getSave_UTC07();
        CdOrder output_UTC07 = output.getSave_UTC07();

        Mockito.when(cdOrderRepositoryMock.save(input_UTC07)).thenThrow(NullPointerException.class);
        CdOrder result = service.save(input_UTC07);

        Assert.assertEquals(output_UTC07, result);
    }

    @Test
    public void testSave_UTC08() {
        CdOrderRepository cdOrderRepositoryMock = Mockito.mock(CdOrderRepository.class);
        service = new CdOrderService(cdOrderRepositoryMock);

        CdOrder input_UTC08 = input.getSave_UTC08();
        CdOrder output_UTC08 = output.getSave_UTC08();

        Mockito.when(cdOrderRepositoryMock.save(input_UTC08)).thenThrow(NullPointerException.class);
        CdOrder result = service.save(input_UTC08);

        Assert.assertEquals(output_UTC08, result);
    }
}
