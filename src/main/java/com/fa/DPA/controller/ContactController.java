package com.fa.DPA.controller;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.dto.ResponseMessage;
import com.fa.DPA.model.CustomerContact;
import com.fa.DPA.repos.ContactRepository;
import com.fa.DPA.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/contact")
public class ContactController {
    private ContactService contactService;
    private ContactRepository contactRepository;

    public ContactController(ContactService contactService, ContactRepository contactRepository) {
        this.contactService = contactService;
        this.contactRepository = contactRepository;
    }

    /**
     *
     * @param raw_page
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getPage(
            @RequestParam(name = "page",defaultValue = Constant.DEFAULT_NUM_PAGE) String raw_page){
        int page = Constant.checkPage(raw_page);
        if(page == -1){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Map<String, Object> response = new HashMap<>();
        List<CustomerContact> contactList ;
        Pageable pageable = PageRequest.of(page, Constant.DEFAULT_PAGE_SIZE);
        try{
            Page<CustomerContact> contactPaging = contactService.getAllContactPaging(pageable);
            contactList = contactPaging.getContent();
            response.put("contactList", contactList);
            response.put("currentPage", contactPaging.getNumber());
            response.put("totalPage", contactPaging.getTotalPages());
            response.put("totalItem", contactPaging.getTotalElements());
        }catch (Exception ex){
            System.out.println(ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    /**
     *
     * @param raw_id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteContact(@PathVariable("id") String raw_id) {
        long id = Constant.checkId(raw_id);
        if(id < 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if(contactRepository.existsById(id)){
        try{
            contactService.softDelete(id);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }}
        else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        try {
            httpHeaders.setLocation(new URI("/contact"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Object> createContact(@RequestBody CustomerContact contact){
        if (contact.getCustomer_name().trim().length()==0){
            return new ResponseEntity<>(new ResponseMessage(true, "Tên không được bỏ trống!"), HttpStatus.BAD_REQUEST);
        }
        if (contact.getPhone().trim().length()==0){
            return new ResponseEntity<>(new ResponseMessage(true, "Số điện thoại không được bỏ trống!"), HttpStatus.BAD_REQUEST);
        }
        if (contact.getAddress().trim().length()==0){
            return new ResponseEntity<>(new ResponseMessage(true, "Địa chỉ không được bỏ trống!"), HttpStatus.BAD_REQUEST);
        }
        CustomerContact contactReturn = contactService.saveContact(contact);
        HttpHeaders httpHeaders = new HttpHeaders();
        if(contactReturn==null){
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
    }


