package com.fa.DPA.StaffAccountService;

import com.fa.DPA.model.Account_Status;
import com.fa.DPA.model.Role;
import com.fa.DPA.model.StaffAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

public class StaffAccountOutput {
    private int checkExistByID_UTC01;
    private int checkExistByID_UTC02;
    private int checkExistByID_UTC03;
    private StaffAccount createAccount_UTC01;
    private StaffAccount createAccount_UTC02;
    private Page<StaffAccount> getAllStaffPaging_UTC01;
    private Page<StaffAccount> getAllStaffPaging_UTC02;

    public StaffAccountOutput() {
        checkExistByID_UTC01 = 1;
        checkExistByID_UTC02 = 0;
        checkExistByID_UTC03 = -1;

        createAccount_UTC01 = new StaffAccount();
        createAccount_UTC01.setStaff_name("Staff name");
        createAccount_UTC01.setAccount_status(new Account_Status());
        createAccount_UTC01.setRole(new Role());
        createAccount_UTC01.setEmail("Staff email");
        createAccount_UTC01.setPhone("0123456789");
        createAccount_UTC01.setUsername("username");
        createAccount_UTC01.setPassword("password");

        createAccount_UTC02 = null;

        List<StaffAccount> staffAccountList = new ArrayList<>();
        staffAccountList.add(createAccount_UTC01);
        getAllStaffPaging_UTC01 = new PageImpl<>(staffAccountList);

        getAllStaffPaging_UTC02= null;
    }

    public int getCheckExistByID_UTC01() {
        return checkExistByID_UTC01;
    }

    public int getCheckExistByID_UTC02() {
        return checkExistByID_UTC02;
    }

    public int getCheckExistByID_UTC03() {
        return checkExistByID_UTC03;
    }

    public StaffAccount getCreateAccount_UTC01() {
        return createAccount_UTC01;
    }

    public StaffAccount getCreateAccount_UTC02() {
        return createAccount_UTC02;
    }

    public Page<StaffAccount> getGetAllStaffPaging_UTC01() {
        return getAllStaffPaging_UTC01;
    }

    public Page<StaffAccount> getGetAllStaffPaging_UTC02() {
        return getAllStaffPaging_UTC02;
    }
}
