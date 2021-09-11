package com.fa.DPA.StaffAccountService;

import com.fa.DPA.model.StaffAccount;
import com.fa.DPA.repos.StaffAccountRepository;
import com.fa.DPA.service.StaffAccountService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class StaffAccountServiceTest {
    StaffAccountInput input = new StaffAccountInput();
    StaffAccountOutput output = new StaffAccountOutput();
    StaffAccountRepository staffAccountRepositoryMock = Mockito.mock(StaffAccountRepository.class);
    StaffAccountService service = new StaffAccountService(staffAccountRepositoryMock);

    @Test
    public void testCheckExistById_UTC01() {
        Long input_UTC01 = input.getCheckExistById_UTC01();
        int output_UTC01 = output.getCheckExistByID_UTC01();

        Mockito.when(staffAccountRepositoryMock.existsStaffAccountById(input_UTC01)).thenReturn(true);
        int result = service.checkExistByID(input_UTC01);

        Assert.assertEquals(output_UTC01, result);
    }

    @Test
    public void testCheckExistById_UTC02() {
        Long input_UTC02 = input.getCheckExistById_UTC02();
        int output_UTC02 = output.getCheckExistByID_UTC02();

        Mockito.when(staffAccountRepositoryMock.existsStaffAccountById(input_UTC02)).thenReturn(false);
        int result = service.checkExistByID(input_UTC02);

        Assert.assertEquals(output_UTC02, result);
    }

    @Test
    public void testCheckExistById_UTC03() {
        Long input_UTC03 = input.getCheckExistById_UTC03();
        int output_UTC03 = output.getCheckExistByID_UTC03();

        Mockito.when(staffAccountRepositoryMock.existsStaffAccountById(input_UTC03)).thenThrow(NullPointerException.class);
        int result = service.checkExistByID(input_UTC03);

        Assert.assertEquals(output_UTC03, result);
    }

    @Test
    public void testGetAllStaffPaging_UTC01() {
        Pageable input_UTC01 = input.getGetAllStaffPaging_UTC01();
        Page<StaffAccount> output_UTC01 = output.getGetAllStaffPaging_UTC01();

        Mockito.when(staffAccountRepositoryMock.findAll(input_UTC01)).thenReturn(output_UTC01);
        Page<StaffAccount> result = service.getAllStaffPaging(input_UTC01);

        Assert.assertEquals(output_UTC01, result);
    }

    @Test
    public void testGetAllStaffPaging_UTC02() {
        Pageable input_UTC02 = input.getGetAllStaffPaging_UTC02();
        Page<StaffAccount> output_UTC02 = output.getGetAllStaffPaging_UTC02();

        Mockito.when(staffAccountRepositoryMock.findAll(input_UTC02)).thenThrow(NullPointerException.class);
        Page<StaffAccount> result = service.getAllStaffPaging(input_UTC02);

        Assert.assertEquals(output_UTC02, result);
    }

    @Test
    public void testCreateAccount_UTC01() {
        StaffAccount input_UTC01 = input.getCreateAccount_UTC01();
        StaffAccount output_UTC01 = output.getCreateAccount_UTC01();

        Mockito.when(staffAccountRepositoryMock.save(input_UTC01)).thenReturn(output_UTC01);
        StaffAccount result = service.save(input_UTC01);

        Assert.assertEquals(output_UTC01, result);
    }

    @Test
    public void testCreateAccount_UTC02() {
        StaffAccount input_UTC02 = input.getCreateAccount_UTC02();
        StaffAccount output_UTC02 = output.getCreateAccount_UTC02();

        Mockito.when(staffAccountRepositoryMock.save(input_UTC02)).thenThrow(NullPointerException.class);
        StaffAccount result = service.save(input_UTC02);

        Assert.assertEquals(output_UTC02, result);
    }

    @Test
    public void testCreateAccount_UTC03() {
        StaffAccount input_UTC03 = input.getCreateAccount_UTC03();
        StaffAccount output_UTC03 = output.getCreateAccount_UTC02();

        Mockito.when(staffAccountRepositoryMock.save(input_UTC03)).thenThrow(NullPointerException.class);
        StaffAccount result = service.save(input_UTC03);

        Assert.assertEquals(output_UTC03, result);
    }

    @Test
    public void testCreateAccount_UTC04() {
        StaffAccount input_UTC04 = input.getCreateAccount_UTC04();
        StaffAccount output_UTC04 = output.getCreateAccount_UTC02();

        Mockito.when(staffAccountRepositoryMock.save(input_UTC04)).thenThrow(NullPointerException.class);
        StaffAccount result = service.save(input_UTC04);

        Assert.assertEquals(output_UTC04, result);
    }

    @Test
    public void testCreateAccount_UTC05() {
        StaffAccount input_UTC05 = input.getCreateAccount_UTC05();
        StaffAccount output_UTC05 = output.getCreateAccount_UTC02();

        Mockito.when(staffAccountRepositoryMock.save(input_UTC05)).thenThrow(NullPointerException.class);
        StaffAccount result = service.save(input_UTC05);

        Assert.assertEquals(output_UTC05, result);
    }

    @Test
    public void testCreateAccount_UTC06() {
        StaffAccount input_UTC06 = input.getCreateAccount_UTC06();
        StaffAccount output_UTC06 = output.getCreateAccount_UTC02();

        Mockito.when(staffAccountRepositoryMock.save(input_UTC06)).thenThrow(NullPointerException.class);
        StaffAccount result = service.save(input_UTC06);

        Assert.assertEquals(output_UTC06, result);
    }

    @Test
    public void testCreateAccount_UTC07() {
        StaffAccount input_UTC07 = input.getCreateAccount_UTC07();
        StaffAccount output_UTC07 = output.getCreateAccount_UTC02();

        Mockito.when(staffAccountRepositoryMock.save(input_UTC07)).thenThrow(NullPointerException.class);
        StaffAccount result = service.save(input_UTC07);

        Assert.assertEquals(output_UTC07, result);
    }

    @Test
    public void testCreateAccount_UTC08() {
        StaffAccount input_UTC08 = input.getCreateAccount_UTC02();
        StaffAccount output_UTC08 = output.getCreateAccount_UTC02();

        Mockito.when(staffAccountRepositoryMock.save(input_UTC08)).thenThrow(NullPointerException.class);
        StaffAccount result = service.save(input_UTC08);

        Assert.assertEquals(output_UTC08, result);
    }

    @Test
    public void testCreateAccount_UTC09() {
        StaffAccount input_UTC09 = input.getCreateAccount_UTC09();
        StaffAccount output_UTC09 = output.getCreateAccount_UTC02();

        Mockito.when(staffAccountRepositoryMock.save(input_UTC09)).thenThrow(NullPointerException.class);
        StaffAccount result = service.save(input_UTC09);

        Assert.assertEquals(output_UTC09, result);
    }
}
