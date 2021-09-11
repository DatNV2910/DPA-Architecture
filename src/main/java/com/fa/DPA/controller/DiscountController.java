package com.fa.DPA.controller;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.dto.ResponseMessage;
import com.fa.DPA.model.Discount;
import com.fa.DPA.repos.BaseRepository;
import com.fa.DPA.repos.DiscountRepository;
import com.fa.DPA.service.DiscountService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/discount")
public class DiscountController {
    private DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }
    @Autowired
    private DiscountRepository repository;


    HttpHeaders httpHeaders = new HttpHeaders();

    /**
     *
     * @param discount
     * @return
     */
    @PostMapping(value = "/create")
    public ResponseEntity<Object> createDiscount(@RequestBody Discount discount) {
        if(discount.getName().trim().length()==0){
            return new ResponseEntity<>(new ResponseMessage(true,"Tên không được bỏ trống!"),HttpStatus.BAD_REQUEST);
        }
        Discount discountReturn = discountService.saveDiscount(discount);
        System.out.println(discountReturn);
        if (discountReturn == null) {
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
     * @param discount
     * @param raw_id
     * @return
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@RequestBody Discount discount, @PathVariable("id") String raw_id) {
        long id = Constant.checkId(raw_id);
        if (id < 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if(discount.getName().trim().length()==0){
            return new ResponseEntity<>(new ResponseMessage(true,"Tên không được bỏ trống!"),HttpStatus.BAD_REQUEST);
        }
        if(repository.existsById(id)){
            discount.setId(id);
            discount = discountService.saveDiscount(discount);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        if (discount == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        try {
            httpHeaders.setLocation(new URI("/discount"));

        } catch (URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

    /**
     *
     * @param raw_page
     * @return
     */
    @GetMapping(value = "/all")
    public ResponseEntity<Map<String, Object>> getPage(
            @RequestParam(name = "page",defaultValue = Constant.DEFAULT_NUM_PAGE) String raw_page) {
        int page = Constant.checkPage(raw_page);
        if (page == -1) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Map<String, Object> responese = new HashMap<>();
        List<Discount> discountsList;
        Pageable pageable = PageRequest.of(page, Constant.DEFAULT_PAGE_SIZE);
        try {
            Page<Discount> discountPaging = discountService.getAllDiscount(pageable);
            discountsList = discountPaging.getContent();
            responese.put("discountList", discountsList);
            responese.put("currentPage", discountPaging.getNumber());
            responese.put("totalPage", discountPaging.getTotalPages());
            responese.put("totalItem", discountPaging.getTotalElements());

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responese, HttpStatus.OK);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Discount> getById(@PathVariable("id") String raw_id){
        long id = Constant.checkId(raw_id);
        if(id < 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Discount discount;
        try{
            if (repository.existsById(id)){
                 discount = discountService.findById(id);
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(discount, HttpStatus.OK);
    }

}




