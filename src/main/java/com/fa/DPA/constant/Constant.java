package com.fa.DPA.constant;

import com.fa.DPA.model.CustomerAccount;
import com.fa.DPA.model.StaffAccount;
import com.fa.DPA.service.CustomerAccountService;
import com.fa.DPA.service.CustomerLoginService.CustomerDetailImpl;
import com.fa.DPA.service.StaffAccountService;
import com.fa.DPA.service.StaffLoginService.StaffDetailImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.EntityNotFoundException;

public class Constant {
    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_CUS = "customer";
    public static final String ROLE_SALE = "sale";
    public static final String ROLE_CUS_CARE = "customer_care";

    public static final String DEFAULT_NUM_PAGE = "0";
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int DEFAULT_PAGE_SIZE_PRODUCT = 12;

    public static final String ACTIVE = "active";
    public static final String DEACTIVE = "deactive";

    public static final String PENDING = "pending";
    public static final long ID_PENDING = 3;
    public static final String ACCOMPLISH = "accomplished";
    public static final long ID_ACCOMPLISH = 1;
    public static final String CANCEL = "canceled";
    public static final long ID_CANCEL = 2;
    public static final String PROCESS = "processing";
    public static final long ID_PROCESS = 4;

    public static final String URL_RESET_PASSWORD = "http://localhost:3000/forgot-password/reset";



    public static final int LIMIT_ORDER_PROCESS = 5;
    public static final String LIMIT_ORDER_PROCESS_MESSAGE = "Can't receive any order due to reach your limit of processing";


    public static final String YOUR_DOMAIN_NAME = "ZHBhLWFyY2hpdGVjdHVyZS5nYQ==";
    public static final String API_KEY = "NjQxZDRlYmY1N2RmNjcwMDAxNTNjZmU2MjU1MmQzYTYtZTMxZGMzY2MtYTA1NzEzZTU=";

    /**
     *
     * @param raw_page
     * @return
     */
    public static int checkPage(String raw_page){
        int page;
        try{
            page = Integer.parseInt(raw_page);
            if(page < 0 || page > Integer.MAX_VALUE){
                return -1;
            }
        }catch (NumberFormatException ex){
            ex.printStackTrace();
            return -1;
        }

        return page;
    }

    /**
     *
     * @param raw_year
     * @return
     */
    public static int checkYear(String raw_year){
        int year;
        try{
            year = Integer.parseInt(raw_year);
            if(year <= 2000 || year > 3000){
                return -1;
            }
        }catch (NumberFormatException ex){
            ex.printStackTrace();
            return -1;
        }
        return year;
    }

    /**
     *
     * @param raw_id
     * @return
     */
    public static long checkId(String raw_id){
        long id;
        try{
            id = Long.parseLong(raw_id);
        }catch (NumberFormatException ex){
            ex.printStackTrace();
            return -1;
        }
        return id;
    }


    /**
     * @return
     */
    public static StaffAccount getStaff(StaffAccountService staffAccountService) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        StaffAccount staff = null;
        if (principal instanceof StaffDetailImpl) {
            String username = ((StaffDetailImpl) principal).getUsername();
            try {
                staff = staffAccountService.findStaffAccountByUsername(username);
            } catch (EntityNotFoundException ex) {
                ex.printStackTrace();
            }

        }
        return staff;
    }

    /**
     * @return
     */
    public static CustomerAccount getCustomer(CustomerAccountService customerAccountService) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CustomerAccount customer = null;
        if (principal instanceof CustomerDetailImpl) {
            String username = ((CustomerDetailImpl) principal).getUsername();
            try {
                customer = customerAccountService.findStaffAccountByUsername(username);
            } catch (EntityNotFoundException ex) {
                ex.printStackTrace();
            }

        }
        return customer;
    }

    /**
     *
     * @param raw_month
     * @return
     */
    public static int checkMonth(String raw_month) {
        int month;
        if(raw_month == null){
            return 0;
        }

        try{
            month = Integer.parseInt(raw_month);
            if(month < 1 || month > 12){
                return -1;
            }
        }catch (NumberFormatException ex){
            ex.printStackTrace();
            return -1;
        }
        return month;
    }

}
