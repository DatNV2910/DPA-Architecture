package com.fa.DPA.controller;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.dto.ResponseMessage;
import com.fa.DPA.model.Category;
import com.fa.DPA.model.StaffAccount;
import com.fa.DPA.repos.CategoryRepository;
import com.fa.DPA.service.CategoryService;
import com.fa.DPA.service.StaffAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService categoryService;
    private StaffAccountService staffAccountService;
    private CategoryRepository repository;

    @Autowired
    public CategoryController(CategoryService categoryService, StaffAccountService staffAccountService, CategoryRepository repository) {
        this.categoryService = categoryService;
        this.staffAccountService = staffAccountService;
        this.repository = repository;
    }




    StaffAccount staffAccount;

    HttpHeaders httpHeaders = new HttpHeaders();

    /**
     * @param category
     * @return
     */
    @PostMapping("/create")
//    @PreAuthorize("hasRole('ROLE_SALE')")
    public ResponseEntity<Object> createCategory(@RequestBody Category category) {
        staffAccount = Constant.getStaff(staffAccountService);
        if (staffAccount == null){
            return new ResponseEntity<>(new ResponseMessage(true,"Cần phải đăng nhập"),HttpStatus.UNAUTHORIZED);
        }
        if(category.getName().trim().length()==0){
            return new ResponseEntity<>(new ResponseMessage(true,"Tên không được bỏ trống!"),HttpStatus.BAD_REQUEST);
        }
        category.setStatus(true);
        Category categoryReturn = categoryService.saveCategory(category);
        if (categoryReturn == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
            httpHeaders.setLocation(new URI("/category"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

    /**
     * @param category
     * @param raw_id
     * @return
     */
    @PutMapping("/{id}")
    //    @PreAuthorize("hasRole('ROLE_SALE')")
    public ResponseEntity<Object> updateCategory(@RequestBody Category category, @PathVariable("id") String raw_id) {
        long id = Constant.checkId(raw_id);
        if(id < 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        staffAccount = Constant.getStaff(staffAccountService);
        if (staffAccount == null){
            return new ResponseEntity<>(new ResponseMessage(true,"Cần phải đăng nhập"),HttpStatus.UNAUTHORIZED);
        }
        if(category.getName().trim().length()==0){
            return new ResponseEntity<>(new ResponseMessage(true,"Tên không được bỏ trống!"),HttpStatus.BAD_REQUEST);
        }
        if (repository.existsById(id)){
            category.setId(id);
            category.setStatus(true);
            category = categoryService.saveCategory(category);
        } else {
            return new ResponseEntity<>(new ResponseMessage(true,"Category không tồn tại"),HttpStatus.BAD_REQUEST);
        }
        if (category == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        try {
            httpHeaders.setLocation(new URI("/category"));

        } catch (URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);

    }

    /**
     * @param raw_page
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getPage(@RequestParam(defaultValue = Constant.DEFAULT_NUM_PAGE) String raw_page) {
        int page = Constant.checkPage(raw_page);
        if (page == -1) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Map<String, Object> response = new HashMap<>();
        List<Category> categoryList;
        Pageable pageable = PageRequest.of(page, Constant.DEFAULT_PAGE_SIZE);
        try {
            Page<Category> categoryPage = categoryService.getAllCategory(pageable);
            categoryList = categoryPage.getContent();
            response.put("categoryList", categoryList);
            response.put("currentPage", categoryPage.getNumber());
            response.put("totalPage", categoryPage.getTotalPages());
            response.put("totalItem", categoryPage.getTotalElements());

        } catch (Exception ex) {
            System.out.println(ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/allConstruction")
    public ResponseEntity<Map<String, Object>> getPageByConstruction(@RequestParam(defaultValue = Constant.DEFAULT_NUM_PAGE) String raw_page) {
        int page = Constant.checkPage(raw_page);
        if (page == -1) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Map<String, Object> response = new HashMap<>();
        List<Category> categoryList;
        Pageable pageable = PageRequest.of(page, Constant.DEFAULT_PAGE_SIZE);
        try {
            Page<Category> categoryPage = categoryService.getAllCategoryByConstruction(pageable);
            categoryList = categoryPage.getContent();
            response.put("categoryList", categoryList);
            response.put("currentPage", categoryPage.getNumber());
            response.put("totalPage", categoryPage.getTotalPages());
            response.put("totalItem", categoryPage.getTotalElements());

        } catch (Exception ex) {
            System.out.println(ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/allInterior")
    public ResponseEntity<Map<String, Object>> getPageByInterior(@RequestParam(defaultValue = Constant.DEFAULT_NUM_PAGE) String raw_page) {
        int page = Constant.checkPage(raw_page);
        if (page == -1) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Map<String, Object> response = new HashMap<>();
        List<Category> categoryList;
        Pageable pageable = PageRequest.of(page, Constant.DEFAULT_PAGE_SIZE);
        try {
            Page<Category> categoryPage = categoryService.getAllCategoryByInteriorDesign(pageable);
            categoryList = categoryPage.getContent();
            response.put("categoryList", categoryList);
            response.put("currentPage", categoryPage.getNumber());
            response.put("totalPage", categoryPage.getTotalPages());
            response.put("totalItem", categoryPage.getTotalElements());

        } catch (Exception ex) {
            System.out.println(ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
