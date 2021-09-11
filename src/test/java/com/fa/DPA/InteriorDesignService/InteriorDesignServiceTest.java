package com.fa.DPA.InteriorDesignService;

import com.fa.DPA.converter.InteriorDesignConverter;
import com.fa.DPA.dto.InteriorDesignDTO;
import com.fa.DPA.model.InteriorDesign;
import com.fa.DPA.repos.InteriorDesignRepository;
import com.fa.DPA.service.InteriorDesignService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class InteriorDesignServiceTest {
    private InteriorDesignInput input = new InteriorDesignInput();
    private InteriorDesignOutput output = new InteriorDesignOutput();
    private InteriorDesignConverter converter = new InteriorDesignConverter();
    private InteriorDesignRepository interiorDesignRepositoryMock = Mockito.mock(InteriorDesignRepository.class);
    private InteriorDesignService service = new InteriorDesignService(interiorDesignRepositoryMock, converter);

    @Test
    public void testSave_UTC01() {
        InteriorDesign input_UTC01 = input.getSave_UTC01();
        InteriorDesign output_UTC01 = output.getSave_UTC01();

        Mockito.when(interiorDesignRepositoryMock.findById(input_UTC01.getId())).thenReturn(java.util.Optional.of(input_UTC01));
        Mockito.when(interiorDesignRepositoryMock.save(input_UTC01)).thenReturn(output_UTC01);
        InteriorDesign result = service.save(input_UTC01);

        Assert.assertEquals(output_UTC01, result);
    }

    @Test
    public void testSave_UTC02() {
        InteriorDesign input_UTC02 = input.getSave_UTC02();
        InteriorDesign output_UTC02 = output.getSave_UTC02();

        Mockito.when(interiorDesignRepositoryMock.save(input_UTC02)).thenReturn(output_UTC02);
        InteriorDesign result = service.save(input_UTC02);

        Assert.assertEquals(output_UTC02, result);
    }

    @Test
    public void testSave_UTC03() {
        InteriorDesign input_UTC03 = input.getSave_UTC03();
        InteriorDesign output_UTC03 = output.getSave_UTC03();

        Mockito.when(interiorDesignRepositoryMock.save(input_UTC03)).thenThrow(NullPointerException.class);
        InteriorDesign result = service.save(input_UTC03);

        Assert.assertEquals(output_UTC03, result);
    }

    @Test
    public void testSave_UTC04() {
        InteriorDesign input_UTC04 = input.getSave_UTC04();
        InteriorDesign output_UTC04 = output.getSave_UTC03();

        Mockito.when(interiorDesignRepositoryMock.save(input_UTC04)).thenThrow(NullPointerException.class);
        InteriorDesign result = service.save(input_UTC04);

        Assert.assertEquals(output_UTC04, result);
    }

    @Test
    public void testSave_UTC05() {
        InteriorDesign input_UTC05 = input.getSave_UTC05();
        InteriorDesign output_UTC05 = output.getSave_UTC03();

        Mockito.when(interiorDesignRepositoryMock.save(input_UTC05)).thenThrow(NullPointerException.class);
        InteriorDesign result = service.save(input_UTC05);

        Assert.assertEquals(output_UTC05, result);
    }

    @Test
    public void testSave_UTC06() {
        InteriorDesign input_UTC06 = input.getSave_UTC06();
        InteriorDesign output_UTC06 = output.getSave_UTC03();

        Mockito.when(interiorDesignRepositoryMock.save(input_UTC06)).thenThrow(NullPointerException.class);
        InteriorDesign result = service.save(input_UTC06);

        Assert.assertEquals(output_UTC06, result);
    }

    @Test
    public void testSave_UTC07() {
        InteriorDesign input_UTC07 = input.getSave_UTC07();
        InteriorDesign output_UTC07 = output.getSave_UTC03();

        Mockito.when(interiorDesignRepositoryMock.save(input_UTC07)).thenThrow(NullPointerException.class);
        InteriorDesign result = service.save(input_UTC07);

        Assert.assertEquals(output_UTC07, result);
    }

    @Test
    public void testSave_UTC08() {
        InteriorDesign input_UTC08 = input.getSave_UTC08();
        InteriorDesign output_UTC08 = output.getSave_UTC03();

        Mockito.when(interiorDesignRepositoryMock.save(input_UTC08)).thenThrow(NullPointerException.class);
        InteriorDesign result = service.save(input_UTC08);

        Assert.assertEquals(output_UTC08, result);
    }

    @Test
    public void testSave_UTC09() {
        InteriorDesign input_UTC09 = input.getSave_UTC09();
        InteriorDesign output_UTC09 = output.getSave_UTC03();

        Mockito.when(interiorDesignRepositoryMock.save(input_UTC09)).thenThrow(NullPointerException.class);
        InteriorDesign result = service.save(input_UTC09);

        Assert.assertEquals(output_UTC09, result);
    }

    @Test
    public void testFindAll_UTC01() {
        Pageable input_UTC01 = input.getFindAll_UTC01();
        List<InteriorDesignDTO> output_UTC01 = output.getFindALl_UTC01();
        List<InteriorDesign> interiorDesigns = new ArrayList<>();
        interiorDesigns.add(output.getSave_UTC01());
        Page<InteriorDesign> interiorDesignPage = new PageImpl<>(interiorDesigns);

        Mockito.when(interiorDesignRepositoryMock.findAll(input_UTC01)).thenReturn(interiorDesignPage);
        List<InteriorDesignDTO> result = service.findAll(input_UTC01);

        Assert.assertEquals(output_UTC01, result);
    }

    @Test
    public void testFindAll_UTC02() {
        Pageable input_UTC02 = input.getFindAll_UTC02();
        List<InteriorDesignDTO> output_UTC02 = output.getFindALl_UTC02();

        Mockito.when(interiorDesignRepositoryMock.findAll(input_UTC02)).thenThrow(NullPointerException.class);
        List<InteriorDesignDTO> result = service.findAll(input_UTC02);

        Assert.assertEquals(output_UTC02, result);
    }

    @Test
    public void testGetInteriorDesignById_UTC01() {
        Long input_UTC01 = input.getGetInteriorDesignById_UTC01();
        InteriorDesignDTO output_UTC01 = output.getGetInteriorDesignById_UTC01();
        InteriorDesign interiorDesign = output.getSave_UTC01();

        Mockito.when(interiorDesignRepositoryMock.findById(input_UTC01)).thenReturn(java.util.Optional.ofNullable(interiorDesign));
        InteriorDesignDTO result = service.getInteriorDesignById(input_UTC01);

        Assert.assertEquals(output_UTC01, result);
    }

    @Test
    public void testGetInteriorDesignById_UTC02() {
        Long input_UTC02 = input.getGetInteriorDesignById_UTC02();
        InteriorDesignDTO output_UTC02 = output.getGetInteriorDesignById_UTC02();

        Mockito.when(interiorDesignRepositoryMock.findById(input_UTC02)).thenThrow(EntityNotFoundException.class);
        InteriorDesignDTO result = service.getInteriorDesignById(input_UTC02);

        Assert.assertEquals(output_UTC02, result);
    }

    @Test
    public void testGetInteriorDesignById_UTC03() {
        Long input_UTC03 = input.getGetInteriorDesignById_UTC03();
        InteriorDesignDTO output_UTC03 = output.getGetInteriorDesignById_UTC02();

        Mockito.when(interiorDesignRepositoryMock.findById(input_UTC03)).thenThrow(NullPointerException.class);
        InteriorDesignDTO result = service.getInteriorDesignById(input_UTC03);

        Assert.assertEquals(output_UTC03, result);
    }
}
