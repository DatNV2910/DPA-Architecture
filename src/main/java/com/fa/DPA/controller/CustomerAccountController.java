package com.fa.DPA.controller;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.controller.output.CustomerAcountOutput;
import com.fa.DPA.controller.output.SubcategoryOutput;
import com.fa.DPA.dto.CustomerAccountDTO;
import com.fa.DPA.dto.ResponseMessage;
import com.fa.DPA.model.CustomerAccount;
import com.fa.DPA.model.Discount;
import com.fa.DPA.repos.CustomerAccountRepository;
import com.fa.DPA.repos.StaffAccountRepository;
import com.fa.DPA.service.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/customer")
public class CustomerAccountController {
    private CustomerAccountService customerAccountService;


    private PasswordEncoder encoder;


    @Autowired
    private StaffAccountRepository staffAccountRepository;

    @Autowired
    public CustomerAccountController(CustomerAccountService customerAccountService, PasswordEncoder passwordEncoder) {
        this.encoder = passwordEncoder;
        this.customerAccountService = customerAccountService;
    }
    @Autowired
    CustomerAccountRepository customerAccountRepository;

    HttpHeaders httpHeaders = new HttpHeaders();

    /**
     *
     * @param customerAccount
     * @return
     */
    @PutMapping("/register")
    public ResponseEntity<CustomerAccount> register(@RequestBody CustomerAccount customerAccount) {
        CustomerAccount customerAccountReturn = customerAccountService.saveAccount(customerAccount);
        if (customerAccountReturn == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            httpHeaders.setLocation(new URI("/home"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

    /**
     *
     * @param raw_id
     * @return
     */
        @GetMapping("/view/{id}")
    public ResponseEntity<CustomerAccount> viewInfo(@PathVariable("id") String raw_id) {
        long id = Constant.checkId(raw_id);
        if (id < 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        CustomerAccount customerAccountReturn;
        try {
            customerAccountReturn = customerAccountService.findById(id);
            customerAccountReturn.setPassword(null);
        } catch (EntityNotFoundException ex) {
            System.out.println(ex);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerAccountReturn, HttpStatus.OK);
    }

    CustomerAccount customerAccountReturn;

    /**
     *
     * @param customerAccount
     * @return
     */
    public ResponseEntity<Object> process(CustomerAccount customerAccount) {
        customerAccountReturn = customerAccountService.saveAccount(customerAccount);
        if (customerAccountReturn == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        long id = customerAccount.getId();
        try {
            httpHeaders.setLocation(new URI("/api/customer/view/" + id));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

    /**
     * @param oldPass
     * @param newPass
     * @return
     */
    @PutMapping("/change-password/save-new")
    public ResponseEntity<Object> editInfo(@RequestParam(name = "oldPass") String oldPass,
                                           @RequestParam(name = "newPass") String newPass) {
        CustomerAccount customerAccount = Constant.getCustomer(customerAccountService);
        if(customerAccount == null){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }


        if (oldPass == null || newPass == null || oldPass.trim().length() == 0 || newPass.trim().length() == 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        //System.out.println(encoder.encode(oldPass));

        System.out.println(customerAccount.getId() + ": " + customerAccount.getPassword());

        boolean check = encoder.matches(oldPass, customerAccount.getPassword());
        if (!check) {
            String mess = "Mật khẩu cũ không khớp!";
            return new ResponseEntity<>(mess, HttpStatus.NOT_ACCEPTABLE);
        }

        customerAccount.setPassword(encoder.encode(newPass));
        return process(customerAccount);
    }


    /**
     * @param customerAccount
     * @return
     */
    @PostMapping("/edit/save")
    public ResponseEntity<Object> editInfo(@RequestBody CustomerAccount customerAccount) {
        CustomerAccount customerAccountCheck = Constant.getCustomer(customerAccountService);
        if (customerAccountCheck == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        if(customerAccount.getEmail().trim().length() == 0){
            return new ResponseEntity<>(new ResponseMessage(true, "Email không được bỏ trống!"), HttpStatus.BAD_REQUEST);
        }
        if(!customerAccount.getEmail().equalsIgnoreCase(customerAccountCheck.getEmail())) {
        if(customerAccountRepository.existsByEmail(customerAccount.getEmail()) || staffAccountRepository.existsByEmail(customerAccount.getEmail()) ){
            return new ResponseEntity<>(new ResponseMessage(true, "Email đã tồn tại!"), HttpStatus.BAD_REQUEST);
        }
        }
        if(customerAccount.getPhone().trim().length() == 0){
            return new ResponseEntity<>(new ResponseMessage(true, "Số điện thoại không được bỏ trống!"), HttpStatus.BAD_REQUEST);
        }
        if(!customerAccount.getPhone().equalsIgnoreCase(customerAccountCheck.getPhone())) {
        if(customerAccountRepository.existsByPhone(customerAccount.getPhone()) || staffAccountRepository.existsByPhone(customerAccount.getPhone())){
            return new ResponseEntity<>(new ResponseMessage(true, "Số điện thoại đã tồn tại"), HttpStatus.BAD_REQUEST);
        }}
        if (customerAccount.getAddress().trim().length()==0){
            return new ResponseEntity<>(new ResponseMessage(true, "Địa chỉ không được bỏ trống!"), HttpStatus.BAD_REQUEST);
        }
        if (customerAccount.getAccount_name().trim().length()==0){
            return new ResponseEntity<>(new ResponseMessage(true, "Tên không được bỏ trống!"), HttpStatus.BAD_REQUEST);
        }
        customerAccount.setPassword(customerAccountCheck.getPassword());
        customerAccount.setUsername(customerAccountCheck.getUsername());
        customerAccount.setCreate_date(customerAccountCheck.getCreate_date());
        CustomerAccount customerAccountReturn = customerAccountService.saveAccount(customerAccount);
        return process(customerAccountReturn);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<CustomerAcountOutput>getPage(@RequestParam(name = "page", defaultValue = Constant.DEFAULT_NUM_PAGE) String raw_page){
        int page = Constant.checkPage(raw_page);
        if(page == -1){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        CustomerAcountOutput result = new CustomerAcountOutput();
        try {
            result.setPage(page);
            Pageable pageable = PageRequest.of(page, Constant.DEFAULT_PAGE_SIZE);
            result.setListResult(customerAccountService.getAllCustomerAccount(pageable));
            result.setTotalPage((int) Math.ceil((double) (customerAccountService.totalItems()) / Constant.DEFAULT_PAGE_SIZE));
            result.setTotalItems(customerAccountService.totalItems());

        } catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
