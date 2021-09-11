package com.fa.DPA.controller;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.dto.ResponseMessage;
import com.fa.DPA.dto.StaffDetailDTO;
import com.fa.DPA.model.StaffAccount;
import com.fa.DPA.repos.CustomerAccountRepository;
import com.fa.DPA.repos.StaffAccountRepository;
import com.fa.DPA.service.OrderService;
import com.fa.DPA.service.StaffAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/api/staff")
public class StaffController {
    private StaffAccountService staffService;
    private OrderService orderService;
    private PasswordEncoder encoder;
    private StaffAccountRepository staffAccountRepository;
    private CustomerAccountRepository customerAccountRepository;

    @Autowired
    public StaffController(StaffAccountService staffService, OrderService orderService, PasswordEncoder encoder, StaffAccountRepository staffAccountRepository, CustomerAccountRepository customerAccountRepository) {
        this.staffService = staffService;
        this.orderService = orderService;
        this.encoder = encoder;
        this.staffAccountRepository = staffAccountRepository;
        this.customerAccountRepository = customerAccountRepository;
    }

    /**
     *
     * @param raw_page
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object> > getAccountPaging(
            @RequestParam(name = "page",defaultValue = Constant.DEFAULT_NUM_PAGE) String raw_page){
        int page = Constant.checkPage(raw_page);
        if(page == -1){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Map<String, Object> response = new HashMap<>();
        List<StaffAccount> staffAccountList;
        Pageable pageable = PageRequest.of(page, Constant.DEFAULT_PAGE_SIZE);
        try{
            Page<StaffAccount> staffAccountPage = staffService.getAllStaffPaging(pageable);
            staffAccountList = staffAccountPage.getContent();
            response.put("contactList", staffAccountList);
            response.put("currentPage", staffAccountPage.getNumber());
            response.put("totalPage", staffAccountPage.getTotalPages());
            response.put("totalItem", staffAccountPage.getTotalElements());
        }catch (Exception ex){
            System.out.println(ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    HttpHeaders httpHeaders = new HttpHeaders();


    /**
     *
     * @param raw_roleId
     * @return
     */
    @GetMapping("/select/all/{roleId}")
    public ResponseEntity<Object> getSelectStaff(@PathVariable("roleId")String raw_roleId){
        long roleId = Constant.checkId(raw_roleId);
        if(roleId == -1){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        List result = staffService.selectAllWithRole(roleId);

        if(result == null){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     *
     * @param staffAccount
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<StaffAccount> createAccount(@RequestBody StaffAccount staffAccount) {
        StaffAccount staffAccountReturn = staffService.createAccount(staffAccount);
        if(staffAccountReturn == null){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
            httpHeaders.setLocation(new URI("/staff"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

    /**
     *
     * @param
     * @return
     */
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> changeRole(@PathVariable("id") String raw_id){
        long sid = Constant.checkId(raw_id);
        if (sid < 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        try{
            staffService.modifyRoleOrStatus(sid, (long) 1);
        }catch(Exception ex){
            System.out.println(ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Đổi chức vụ thành công!", HttpStatus.OK);
    }


    /**
     *
     * @param
     * @return
     */

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deactiveAccount(@PathVariable("id") String raw_id){
        long sid = Constant.checkId(raw_id);
        if (sid < 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        try{
            staffService.modifyRoleOrStatus(sid, null);
        }catch(Exception ex){
            System.out.println(ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Hủy kích hoạt thành công!", HttpStatus.OK);
    }

    /**
     *
     * @param oldPass
     * @param newPass
     * @return
     */
    @PutMapping("/change-password/save-new")
    public ResponseEntity<Object> ChangePassword(@RequestParam(name = "oldPass") String oldPass,
                                           @RequestParam(name = "newPass") String newPass) {
        String mess;
        StaffAccount staffAccount = Constant.getStaff(staffService);
        if(staffAccount == null){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        if (oldPass == null || newPass == null || oldPass.trim().length() == 0 || newPass.trim().length() == 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        //System.out.println(encoder.encode(oldPass));

        System.out.println(staffAccount.getId() + ": " + staffAccount.getPassword());

        boolean check = encoder.matches(oldPass, staffAccount.getPassword());
        if (!check) {
            mess = "Mật khẩu cũ không đúng!";
            return new ResponseEntity<>(mess, HttpStatus.NOT_ACCEPTABLE);
        }

        staffAccount.setPassword(encoder.encode(newPass));
        StaffAccount staffAccountReturn = staffService.save(staffAccount);
        if (staffAccountReturn == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        mess = "Đổi mật khẩu thành công";
        return new ResponseEntity<>(mess, HttpStatus.OK);
    }

    @PutMapping("/modify")
    public ResponseEntity<Object> modify(@RequestBody StaffAccount staffAccount){
        StaffAccount staffAccountCheck = Constant.getStaff(staffService);
        if(staffAccountCheck == null){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        if(!staffAccount.getEmail().equalsIgnoreCase(staffAccountCheck.getEmail())) {
            if(customerAccountRepository.existsByEmail(staffAccount.getEmail()) || staffAccountRepository.existsByEmail(staffAccount.getEmail()) ){
                return new ResponseEntity<>(new ResponseMessage(true, "Email đã tồn tại!"), HttpStatus.BAD_REQUEST);
            }
        }
        if(!staffAccount.getPhone().equalsIgnoreCase(staffAccountCheck.getPhone())) {
            if(customerAccountRepository.existsByPhone(staffAccount.getPhone()) || staffAccountRepository.existsByPhone(staffAccount.getPhone())){
                return new ResponseEntity<>(new ResponseMessage(true, "Số điện thoại đã tồn tại"), HttpStatus.BAD_REQUEST);
            }}
        staffAccount.setPassword(staffAccountCheck.getPassword());
        staffAccount.setUsername(staffAccountCheck.getUsername());
        staffAccount.setAccount_status(staffAccountCheck.getAccount_status());
        staffAccount.setRole(staffAccountCheck.getRole());
        staffAccount.setCreate_date(staffAccountCheck.getCreate_date());
        StaffAccount staffAccountReturn = staffService.save(staffAccount);
        if(staffAccountReturn == null){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Cập nhập thông tin thành công!", HttpStatus.OK);
    }

    /**
     *
     * @param raw_id
     * @return
     */
    @GetMapping("/view/{id}")
    public ResponseEntity<Object> viewDetail(@PathVariable("id") String raw_id){

        long id = Constant.checkId(raw_id);
        if(id == -1){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        StaffAccount staffAccount;
        try {
            staffAccount = staffService.findById(id);
        }catch (EntityNotFoundException ex){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        long numberFinishingOrder = orderService.findNumberOfFinishingOrder(id);

        if(numberFinishingOrder == -1){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        StaffDetailDTO staffDetailDTO = new StaffDetailDTO(staffAccount, numberFinishingOrder);

        return new ResponseEntity<>(staffDetailDTO, HttpStatus.OK);

    }

}

