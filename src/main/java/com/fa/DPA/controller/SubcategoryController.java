package com.fa.DPA.controller;

import com.fa.DPA.constant.Constant;

import com.fa.DPA.controller.output.SubcategoryOutput;
import com.fa.DPA.dto.ResponseMessage;
import com.fa.DPA.model.StaffAccount;
import com.fa.DPA.model.SubCategory;
import com.fa.DPA.repos.SubCategoryRepository;
import com.fa.DPA.service.StaffAccountService;
import com.fa.DPA.service.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/sale")
public class SubcategoryController {
    private SubcategoryService service;
    private StaffAccountService staffAccountService;
    private SubCategoryRepository repository;

    @Autowired
    public SubcategoryController(SubcategoryService service, StaffAccountService staffAccountService, SubCategoryRepository repository) {
        this.service = service;
        this.staffAccountService = staffAccountService;
        this.repository = repository;
    }

    HttpHeaders httpHeaders = new HttpHeaders();

    StaffAccount staffAccount;

    /**
     *
     * @param subCategory
     * @return
     */
    @PostMapping("/subcategory")
//    @PreAuthorize("hasRole('ROLE_SALE')")
    public ResponseEntity<Object> createSubcategory(@RequestBody SubCategory subCategory){
        staffAccount = Constant.getStaff(staffAccountService);
        if(staffAccount == null){
            return new ResponseEntity<>(new ResponseMessage(true,"Cần phải đăng nhập"),HttpStatus.UNAUTHORIZED);
        }
        if(subCategory.getName().trim().length()==0){
            return new ResponseEntity<>(new ResponseMessage(true,"Tên không được bỏ trống!"),HttpStatus.BAD_REQUEST);
        }
        subCategory.setStatus(true);
        SubCategory subcategoryReturn = service.saveSubcategory(subCategory);

        if(subcategoryReturn == null){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
            httpHeaders.setLocation(new URI("/subcategory"));

        } catch (URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }


    /**
     *
     * @param subCategory
     * @param raw_id
     * @return
     */
    @PutMapping(value = "/subcategory/{id}")
    public ResponseEntity<Object> updateSubcategory(@RequestBody SubCategory subCategory,
                                                         @PathVariable("id") String raw_id){
        Long id = Constant.checkId(raw_id);
        if(id == -1){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        staffAccount = Constant.getStaff(staffAccountService);
        if (staffAccount==null){
            return new ResponseEntity<>(new ResponseMessage(true,"Cần phải đăng nhập"),HttpStatus.UNAUTHORIZED);
        }
        if(subCategory.getName().trim().length()==0){
            return new ResponseEntity<>(new ResponseMessage(true,"Tên không được bỏ trống!"),HttpStatus.BAD_REQUEST);
        }
        if (repository.existsById(id)){
            subCategory.setId(id);
            subCategory.setStatus(true);
            subCategory = service.saveSubcategory(subCategory);
        } else {
            return new ResponseEntity<>(new ResponseMessage(true,"SubCategory không tồn tại"),HttpStatus.BAD_REQUEST);
        }
        if(subCategory == null){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try{
            httpHeaders.setLocation(new URI("/subcategory"));

        }  catch (URISyntaxException e) {
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
    @GetMapping(value = "/subcategory")
    public ResponseEntity<SubcategoryOutput> showSubcategory(@RequestParam(defaultValue = Constant.DEFAULT_NUM_PAGE) String raw_page){
        int page = Constant.checkPage(raw_page);
        if(page == -1){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        SubcategoryOutput result = new SubcategoryOutput();
        try{
        result.setPage(page);
        Pageable pageable = PageRequest.of(page, Constant.DEFAULT_PAGE_SIZE);
        result.setListResut(service.findAll(pageable));
        result.setTotalPage((int) Math.ceil((double) (service.totalItems()) / Constant.DEFAULT_PAGE_SIZE));
        result.setTotalItems(service.totalItems());}
        catch (Exception exception){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);

    }
    @GetMapping(value = "/allByCategory/{category_id}")
    public ResponseEntity<SubcategoryOutput> showSubcategoryByCategory(@RequestParam(defaultValue = Constant.DEFAULT_NUM_PAGE) String raw_page ,
                                                                       @PathVariable("category_id") String category_id){
        int page = Constant.checkPage(raw_page);
        if(page == -1){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        long categoryid = Constant.checkId(category_id);
        if(categoryid == -1){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        SubcategoryOutput result = new SubcategoryOutput();
        try{
            result.setPage(page);
            Pageable pageable = PageRequest.of(page, Constant.DEFAULT_PAGE_SIZE);
            result.setListResut(service.getAllSubcategoryByCategory(pageable, categoryid));
            result.setTotalPage((int) Math.ceil((double) (service.totalItems()) / Constant.DEFAULT_PAGE_SIZE));
            result.setTotalItems(service.totalItems());}
        catch (Exception exception){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);


    }

}
