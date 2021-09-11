package com.fa.DPA.ConstructDrawingService;

import com.fa.DPA.model.ConstructionDrawing;
import com.fa.DPA.repos.ConstructionDrawingRepository;
import com.fa.DPA.service.ConstructionDrawingService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ConstructDrawingServiceTest {
    private ConstructDrawingInput input = new ConstructDrawingInput();
    private ConstructDrawingOutput output = new ConstructDrawingOutput();
    ConstructionDrawingService service;

    @Test
    public void testGetAllPage_01() {
        ConstructionDrawingRepository constructionDrawingRepositoryMock = Mockito.mock(ConstructionDrawingRepository.class);
        service = new ConstructionDrawingService(constructionDrawingRepositoryMock);

        Pageable input_UTC01 = input.getGetAllPage_UTC01();
        Page<ConstructionDrawing> output_UTC01 = output.getGetAllPage_UTC01();

        Mockito.when(constructionDrawingRepositoryMock.findAllByStatusTrue(input_UTC01)).thenReturn(output_UTC01);
        Page<ConstructionDrawing> result = service.getAllPage(input_UTC01, -1, false);

        Assert.assertEquals(output_UTC01, result);
    }

    @Test
    public void testGetAllPage_02() {
        ConstructionDrawingRepository constructionDrawingRepositoryMock = Mockito.mock(ConstructionDrawingRepository.class);
        service = new ConstructionDrawingService(constructionDrawingRepositoryMock);

        Pageable input_UTC02 = input.getGetAllPage_UTC02();
        Page<ConstructionDrawing> output_UTC02 = output.getGetAllPage_UTC02();

        Mockito.when(constructionDrawingRepositoryMock.findAllByStatusTrue(input_UTC02)).thenThrow(NullPointerException.class);
        Page<ConstructionDrawing> result = service.getAllPage(input_UTC02, -1, false);

        Assert.assertEquals(output_UTC02, result);
    }

    @Test
    public void testGetAllPage_03() {
        ConstructionDrawingRepository constructionDrawingRepositoryMock = Mockito.mock(ConstructionDrawingRepository.class);
        service = new ConstructionDrawingService(constructionDrawingRepositoryMock);

        Pageable input_UTC03 = input.getGetAllPage_UTC01();
        Page<ConstructionDrawing> output_UTC03 = output.getGetAllPage_UTC01();

        Mockito.when(constructionDrawingRepositoryMock.findWithHighestSold(input_UTC03)).thenReturn(output_UTC03);
        Page<ConstructionDrawing> result = service.getAllPage(input_UTC03, 0, true);

        Assert.assertEquals(output_UTC03, result);
    }

    @Test
    public void testGetAllPage_04() {
        ConstructionDrawingRepository constructionDrawingRepositoryMock = Mockito.mock(ConstructionDrawingRepository.class);
        service = new ConstructionDrawingService(constructionDrawingRepositoryMock);

        Pageable input_UTC04 = input.getGetAllPage_UTC01();
        Page<ConstructionDrawing> output_UTC04 = output.getGetAllPage_UTC01();

        Mockito.when(constructionDrawingRepositoryMock.findAll(input_UTC04)).thenReturn(output_UTC04);
        Page<ConstructionDrawing> result = service.getAllPage(input_UTC04, -1, true);

        Assert.assertEquals(output_UTC04, result);
    }

    @Test
    public void testGetAllPage_05() {
        ConstructionDrawingRepository constructionDrawingRepositoryMock = Mockito.mock(ConstructionDrawingRepository.class);
        service = new ConstructionDrawingService(constructionDrawingRepositoryMock);

        Pageable input_UTC05 = input.getGetAllPage_UTC02();
        Page<ConstructionDrawing> output_UTC05 = output.getGetAllPage_UTC02();

        Mockito.when(constructionDrawingRepositoryMock.findAll(input_UTC05)).thenThrow(NullPointerException.class);
        Page<ConstructionDrawing> result = service.getAllPage(input_UTC05, -1, true);

        Assert.assertEquals(output_UTC05, result);
    }

    @Test
    public void testSave_UTC01() {
        ConstructionDrawingRepository constructionDrawingRepositoryMock = Mockito.mock(ConstructionDrawingRepository.class);
        service = new ConstructionDrawingService(constructionDrawingRepositoryMock);

        ConstructionDrawing input_UTC01 = input.getSave_UTC01();
        ConstructionDrawing output_UTC01 = output.getSave_UTC01();

        Mockito.when(constructionDrawingRepositoryMock.save(input_UTC01)).thenReturn(output_UTC01);
        ConstructionDrawing result = service.save(input_UTC01);

        Assert.assertEquals(output_UTC01, result);
    }

    @Test
    public void testSave_UTC02() {
        ConstructionDrawingRepository constructionDrawingRepositoryMock = Mockito.mock(ConstructionDrawingRepository.class);
        service = new ConstructionDrawingService(constructionDrawingRepositoryMock);

        ConstructionDrawing input_UTC02 = input.getSave_UTC02();
        ConstructionDrawing output_UTC02 = output.getSave_UTC02();

        Mockito.when(constructionDrawingRepositoryMock.save(input_UTC02)).thenReturn(output_UTC02);
        ConstructionDrawing result = service.save(input_UTC02);

        Assert.assertEquals(output_UTC02, result);
    }

    @Test
    public void testSave_UTC03() {
        ConstructionDrawingRepository constructionDrawingRepositoryMock = Mockito.mock(ConstructionDrawingRepository.class);
        service = new ConstructionDrawingService(constructionDrawingRepositoryMock);

        ConstructionDrawing input_UTC03 = input.getSave_UTC03();
        ConstructionDrawing output_UTC03 = output.getSave_UTC03();

        Mockito.when(constructionDrawingRepositoryMock.save(input_UTC03)).thenThrow(NullPointerException.class);
        ConstructionDrawing result = service.save(input_UTC03);

        Assert.assertEquals(output_UTC03, result);
    }

    @Test
    public void testSave_UTC04() {
        ConstructionDrawingRepository constructionDrawingRepositoryMock = Mockito.mock(ConstructionDrawingRepository.class);
        service = new ConstructionDrawingService(constructionDrawingRepositoryMock);

        ConstructionDrawing input_UTC04 = input.getSave_UTC04();
        ConstructionDrawing output_UTC04 = output.getSave_UTC03();

        Mockito.when(constructionDrawingRepositoryMock.save(input_UTC04)).thenThrow(NullPointerException.class);
        ConstructionDrawing result = service.save(input_UTC04);

        Assert.assertEquals(output_UTC04, result);
    }

    @Test
    public void testSave_UTC05() {
        ConstructionDrawingRepository constructionDrawingRepositoryMock = Mockito.mock(ConstructionDrawingRepository.class);
        service = new ConstructionDrawingService(constructionDrawingRepositoryMock);

        ConstructionDrawing input_UTC05 = input.getSave_UTC05();
        ConstructionDrawing output_UTC05 = output.getSave_UTC03();

        Mockito.when(constructionDrawingRepositoryMock.save(input_UTC05)).thenThrow(NullPointerException.class);
        ConstructionDrawing result = service.save(input_UTC05);

        Assert.assertEquals(output_UTC05, result);
    }

    @Test
    public void testSave_UTC06() {
        ConstructionDrawingRepository constructionDrawingRepositoryMock = Mockito.mock(ConstructionDrawingRepository.class);
        service = new ConstructionDrawingService(constructionDrawingRepositoryMock);

        ConstructionDrawing input_UTC06 = input.getSave_UTC06();
        ConstructionDrawing output_UTC06 = output.getSave_UTC03();

        Mockito.when(constructionDrawingRepositoryMock.save(input_UTC06)).thenThrow(NullPointerException.class);
        ConstructionDrawing result = service.save(input_UTC06);

        Assert.assertEquals(output_UTC06, result);
    }

    @Test
    public void testSave_UTC07() {
        ConstructionDrawingRepository constructionDrawingRepositoryMock = Mockito.mock(ConstructionDrawingRepository.class);
        service = new ConstructionDrawingService(constructionDrawingRepositoryMock);

        ConstructionDrawing input_UTC07 = input.getSave_UTC07();
        ConstructionDrawing output_UTC07 = output.getSave_UTC03();

        Mockito.when(constructionDrawingRepositoryMock.save(input_UTC07)).thenThrow(NullPointerException.class);
        ConstructionDrawing result = service.save(input_UTC07);

        Assert.assertEquals(output_UTC07, result);
    }

    @Test
    public void testSave_UTC08() {
        ConstructionDrawingRepository constructionDrawingRepositoryMock = Mockito.mock(ConstructionDrawingRepository.class);
        service = new ConstructionDrawingService(constructionDrawingRepositoryMock);

        ConstructionDrawing input_UTC08 = input.getSave_UTC08();
        ConstructionDrawing output_UTC08 = output.getSave_UTC03();

        Mockito.when(constructionDrawingRepositoryMock.save(input_UTC08)).thenThrow(NullPointerException.class);
        ConstructionDrawing result = service.save(input_UTC08);

        Assert.assertEquals(output_UTC08, result);
    }

    @Test
    public void testSave_UTC09() {
        ConstructionDrawingRepository constructionDrawingRepositoryMock = Mockito.mock(ConstructionDrawingRepository.class);
        service = new ConstructionDrawingService(constructionDrawingRepositoryMock);

        ConstructionDrawing input_UTC09 = input.getSave_UTC09();
        ConstructionDrawing output_UTC09 = output.getSave_UTC03();

        Mockito.when(constructionDrawingRepositoryMock.save(input_UTC09)).thenThrow(NullPointerException.class);
        ConstructionDrawing result = service.save(input_UTC09);

        Assert.assertEquals(output_UTC09, result);
    }

    @Test
    public void testSave_UTC10() {
        ConstructionDrawingRepository constructionDrawingRepositoryMock = Mockito.mock(ConstructionDrawingRepository.class);
        service = new ConstructionDrawingService(constructionDrawingRepositoryMock);

        ConstructionDrawing input_UTC10 = input.getSave_UTC10();
        ConstructionDrawing output_UTC10 = output.getSave_UTC03();

        Mockito.when(constructionDrawingRepositoryMock.save(input_UTC10)).thenThrow(NullPointerException.class);
        ConstructionDrawing result = service.save(input_UTC10);

        Assert.assertEquals(output_UTC10, result);
    }

    @Test
    public void testSave_UTC11() {
        ConstructionDrawingRepository constructionDrawingRepositoryMock = Mockito.mock(ConstructionDrawingRepository.class);
        service = new ConstructionDrawingService(constructionDrawingRepositoryMock);

        ConstructionDrawing input_UTC11 = input.getSave_UTC11();
        ConstructionDrawing output_UTC11 = output.getSave_UTC03();

        Mockito.when(constructionDrawingRepositoryMock.save(input_UTC11)).thenThrow(NullPointerException.class);
        ConstructionDrawing result = service.save(input_UTC11);

        Assert.assertEquals(output_UTC11, result);
    }

    @Test
    public void testSave_UTC12() {
        ConstructionDrawingRepository constructionDrawingRepositoryMock = Mockito.mock(ConstructionDrawingRepository.class);
        service = new ConstructionDrawingService(constructionDrawingRepositoryMock);

        ConstructionDrawing input_UTC12 = input.getSave_UTC12();
        ConstructionDrawing output_UTC12 = output.getSave_UTC03();

        Mockito.when(constructionDrawingRepositoryMock.save(input_UTC12)).thenThrow(NullPointerException.class);
        ConstructionDrawing result = service.save(input_UTC12);

        Assert.assertEquals(output_UTC12, result);
    }

    @Test
    public void testSave_UTC13() {
        ConstructionDrawingRepository constructionDrawingRepositoryMock = Mockito.mock(ConstructionDrawingRepository.class);
        service = new ConstructionDrawingService(constructionDrawingRepositoryMock);

        ConstructionDrawing input_UTC13 = input.getSave_UTC13();
        ConstructionDrawing output_UTC13 = output.getSave_UTC03();

        Mockito.when(constructionDrawingRepositoryMock.save(input_UTC13)).thenThrow(NullPointerException.class);
        ConstructionDrawing result = service.save(output_UTC13);

        Assert.assertEquals(output_UTC13, result);
    }
}
