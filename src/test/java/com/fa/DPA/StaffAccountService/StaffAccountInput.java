package com.fa.DPA.StaffAccountService;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.model.Account_Status;
import com.fa.DPA.model.Role;
import com.fa.DPA.model.StaffAccount;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class StaffAccountInput {
    private Long checkExistById_UTC01;
    private Long checkExistById_UTC02;
    private Long checkExistById_UTC03;
    private StaffAccount createAccount_UTC01;
    private StaffAccount createAccount_UTC02;
    private StaffAccount createAccount_UTC03;
    private StaffAccount createAccount_UTC04;
    private StaffAccount createAccount_UTC05;
    private StaffAccount createAccount_UTC06;
    private StaffAccount createAccount_UTC07;
    private StaffAccount createAccount_UTC08;
    private StaffAccount createAccount_UTC09;
    private Pageable getAllStaffPaging_UTC01;
    private Pageable getAllStaffPaging_UTC02;

    public StaffAccountInput() {
        checkExistById_UTC01 = Long.valueOf(1);
        checkExistById_UTC02 = Long.valueOf(-1);
        checkExistById_UTC03 = null;

        createAccount_UTC01 = new StaffAccount();
        createAccount_UTC01.setStaff_name("Staff name");
        createAccount_UTC01.setAccount_status(new Account_Status());
        createAccount_UTC01.setRole(new Role());
        createAccount_UTC01.setEmail("Staff email");
        createAccount_UTC01.setPhone("0123456789");
        createAccount_UTC01.setUsername("username");
        createAccount_UTC01.setPassword("password");

        createAccount_UTC02 = new StaffAccount();
        createAccount_UTC02.setStaff_name("Staff name");
        createAccount_UTC02.setRole(new Role());
        createAccount_UTC02.setEmail("Staff email");
        createAccount_UTC02.setPhone("0123456789");
        createAccount_UTC02.setUsername("username");
        createAccount_UTC02.setPassword("password");

        createAccount_UTC03 = new StaffAccount();
        createAccount_UTC03.setStaff_name("Staff name");
        createAccount_UTC03.setAccount_status(new Account_Status());
        createAccount_UTC03.setEmail("Staff email");
        createAccount_UTC03.setPhone("0123456789");
        createAccount_UTC03.setUsername("username");
        createAccount_UTC03.setPassword("password");

        createAccount_UTC04 = new StaffAccount();
        createAccount_UTC04.setStaff_name("Staff name");
        createAccount_UTC04.setAccount_status(new Account_Status());
        createAccount_UTC04.setRole(new Role());
        createAccount_UTC04.setPhone("0123456789");
        createAccount_UTC04.setUsername("username");
        createAccount_UTC04.setPassword("password");

        createAccount_UTC05 = new StaffAccount();
        createAccount_UTC05.setStaff_name("Staff name");
        createAccount_UTC05.setAccount_status(new Account_Status());
        createAccount_UTC05.setRole(new Role());
        createAccount_UTC05.setEmail("Staff email");
        createAccount_UTC05.setUsername("username");
        createAccount_UTC05.setPassword("password");

        createAccount_UTC06 = new StaffAccount();
        createAccount_UTC06.setStaff_name("Staff name");
        createAccount_UTC06.setAccount_status(new Account_Status());
        createAccount_UTC06.setRole(new Role());
        createAccount_UTC06.setEmail("Staff email");
        createAccount_UTC06.setPhone("0123456789");
        createAccount_UTC06.setPassword("password");

        createAccount_UTC07 = new StaffAccount();
        createAccount_UTC07.setStaff_name("Staff name");
        createAccount_UTC07.setAccount_status(new Account_Status());
        createAccount_UTC07.setRole(new Role());
        createAccount_UTC07.setEmail("Staff email");
        createAccount_UTC07.setPhone("0123456789");
        createAccount_UTC07.setUsername("username");

        createAccount_UTC08 = new StaffAccount();
        createAccount_UTC08.setAccount_status(new Account_Status());
        createAccount_UTC08.setRole(new Role());
        createAccount_UTC08.setEmail("Staff email");
        createAccount_UTC08.setPhone("0123456789");
        createAccount_UTC08.setUsername("username");
        createAccount_UTC08.setPassword("password");

        createAccount_UTC09 = null;

        getAllStaffPaging_UTC01 = PageRequest.of(0, Constant.DEFAULT_PAGE_SIZE);
        getAllStaffPaging_UTC02 = null;
    }

    public StaffAccount getCreateAccount_UTC01() {
        return createAccount_UTC01;
    }

    public StaffAccount getCreateAccount_UTC02() {
        return createAccount_UTC02;
    }

    public StaffAccount getCreateAccount_UTC03() {
        return createAccount_UTC03;
    }

    public StaffAccount getCreateAccount_UTC04() {
        return createAccount_UTC04;
    }

    public StaffAccount getCreateAccount_UTC05() {
        return createAccount_UTC05;
    }

    public StaffAccount getCreateAccount_UTC06() {
        return createAccount_UTC06;
    }

    public StaffAccount getCreateAccount_UTC07() {
        return createAccount_UTC07;
    }

    public Pageable getGetAllStaffPaging_UTC01() {
        return getAllStaffPaging_UTC01;
    }

    public Pageable getGetAllStaffPaging_UTC02() {
        return getAllStaffPaging_UTC02;
    }

    public StaffAccount getCreateAccount_UTC08() {
        return createAccount_UTC08;
    }

    public StaffAccount getCreateAccount_UTC09() {
        return createAccount_UTC09;
    }

    public Long getCheckExistById_UTC01() {
        return checkExistById_UTC01;
    }

    public Long getCheckExistById_UTC02() {
        return checkExistById_UTC02;
    }

    public Long getCheckExistById_UTC03() {
        return checkExistById_UTC03;
    }
}
