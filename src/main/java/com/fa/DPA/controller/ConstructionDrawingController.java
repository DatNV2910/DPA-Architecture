package com.fa.DPA.controller;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.dto.ConstructionDrawingDTO;
import com.fa.DPA.dto.OrderDTO;
import com.fa.DPA.model.ConstructionDrawing;
import com.fa.DPA.model.Order;
import com.fa.DPA.model.StaffAccount;
import com.fa.DPA.repos.ConstructionDrawingRepository;
import com.fa.DPA.service.ConstructionDrawingService;
import com.fa.DPA.service.StaffAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/product")
public class ConstructionDrawingController {
    private ConstructionDrawingService constructionDrawingService;
    private ConstructionDrawingRepository repository;
    private StaffAccountService staffAccountService;

    @Autowired
    public ConstructionDrawingController(ConstructionDrawingService constructionDrawingService, StaffAccountService staffAccountService) {
        this.constructionDrawingService = constructionDrawingService;
        this.staffAccountService = staffAccountService;
    }

    public static final String[] SORT_PRODUCT = {"newest_product", "oldest_product", "highest_sold", "more_expensive_product",
            "cheaper_product"};


    /**
     * @param page
     * @param search
     * @param filter
     * @param sortType
     * @return
     */
    public ResponseEntity<Map<String, Object>> process(int page, String search, String filter, String sortType) {
        boolean check = false;
        for (String s : SORT_PRODUCT) {
            if (sortType.equals(s)) {
                check = true;
            }
        }
        if (!check) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Map<String, Object> response = new HashMap<>();
        List<ConstructionDrawing> constructionDrawings;
        List<ConstructionDrawingDTO> drawingDTOS = new ArrayList<>();
        Pageable pageable = null;
        Sort sorts = null;
        int check_outside = -1;
        boolean isStaff = false;
        switch (sortType) {
            case "newest_product":
                sorts = Sort.by("id").descending();
                break;
            case "oldest_product":
                sorts = Sort.by("id").ascending();
                break;

            case "highest_sold":
                check_outside = 0;
                break;

            case "more_expensive_product":
                sorts = Sort.by("price").descending();
                break;
            case "cheaper_product":
                sorts = Sort.by("price").ascending();
                break;

        }

        StaffAccount staffAccount = Constant.getStaff(staffAccountService);

        if(staffAccount != null){
            isStaff = true;
        }

        if (check_outside != 0) {
            pageable = PageRequest.of(page, Constant.DEFAULT_PAGE_SIZE_PRODUCT, sorts);
        } else {
            pageable = PageRequest.of(page, Constant.DEFAULT_PAGE_SIZE_PRODUCT);
        }

        int searchLength = search.trim().length();

        int filterLength = filter.trim().length();

        int type = -1;

        if (searchLength != 0) {
            type = 0;
        }

        if (filterLength != 0) {
            type = 1;
        }

        if (searchLength != 0 && filterLength != 0) {
            type = 2;
        }



        Page<ConstructionDrawing> drawingPage;
        try {

            drawingPage = constructionDrawingService.getAllPageWithCondition(pageable, check_outside, isStaff,
                    type, search, filter);

            constructionDrawings = drawingPage.getContent();

            for (ConstructionDrawing c : constructionDrawings) {

//                System.out.println(c.toString());
//                String getName = c.getName().toLowerCase();
//                String getSubCategory = c.getSubCategory().getName().toLowerCase();
//                switch (type) {
//                    case 0:
//                        if(getName.contains(search.toLowerCase())){
//                            ConstructionDrawingDTO drawingDTO = new ConstructionDrawingDTO(c);
//                            drawingDTOS.add(drawingDTO);
//                        }
//                        break;
//                    case 1:
//                        if(getSubCategory.contains(filter.toLowerCase())){
//                            ConstructionDrawingDTO drawingDTO = new ConstructionDrawingDTO(c);
//                            drawingDTOS.add(drawingDTO);
//                        }
//                        break;
//
//                    case 2:
//                        if (getName.contains(search.toLowerCase()) && c.getSubCategory().getName().contains(filter)) {
//                            ConstructionDrawingDTO drawingDTO = new ConstructionDrawingDTO(c);
//                            drawingDTOS.add(drawingDTO);
//                        }
//                        break;
//
//                    default:
//                        ConstructionDrawingDTO drawingDTO = new ConstructionDrawingDTO(c);
//                        drawingDTOS.add(drawingDTO);
//                        break;
//                }
//
                ConstructionDrawingDTO drawingDTO = new ConstructionDrawingDTO(c);
                drawingDTOS.add(drawingDTO);
         }

            System.out.println(drawingDTOS);
            response.put("drawingList", drawingDTOS);
            response.put("currentPage", page);
            response.put("totalPage", drawingPage.getTotalPages());
            response.put("totalItem", drawingDTOS.size());
            response.put("totalDatabaseItem", drawingPage.getTotalElements());
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /**
     * @param raw_page
     * @param search
     * @param filter
     * @param sort
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> listConstructionDraw(
            @RequestParam(name = "page", defaultValue = Constant.DEFAULT_NUM_PAGE) String raw_page,
            @RequestParam(name = "search", defaultValue = "") String search,
            @RequestParam(name = "filter", defaultValue = "") String filter,
            @RequestParam(name = "sort", defaultValue = "newest_product") String sort) {
        int page = Constant.checkPage(raw_page);
        if (page == -1) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return process(page, search, filter, sort);
    }


    /**
     * @param raw_id
     * @return
     */
    @GetMapping("/detail/{id}")
    public ResponseEntity<ConstructionDrawing> viewDetail(@PathVariable("id") String raw_id) {
        long id = Constant.checkId(raw_id);
        if (id < 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        ConstructionDrawing constructionDrawing;
        try {
            constructionDrawing = constructionDrawingService.findById(id);
        } catch (EntityNotFoundException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(constructionDrawing, HttpStatus.OK);
    }

    HttpHeaders httpHeaders = new HttpHeaders();


    ConstructionDrawing constructionDrawingReturn;

    /**
     * @param constructionDrawing
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<ConstructionDrawing> addConstructionDrawing(@RequestBody ConstructionDrawing constructionDrawing) {
        StaffAccount staffAccount = Constant.getStaff(staffAccountService);
        if(staffAccount == null){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        constructionDrawing.setStatus(true);
        constructionDrawing.setStaffAccount(staffAccount);
        constructionDrawingReturn = constructionDrawingService.save(constructionDrawing);
        if(constructionDrawingReturn == null){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
            httpHeaders.setLocation(new URI("/product"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }


    /**
     * @param constructionDrawing
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity<Object> modify(@RequestBody ConstructionDrawing constructionDrawing) {
        ConstructionDrawing constructionDrawingFind;

        try {
            constructionDrawingFind = constructionDrawingService.findById(constructionDrawing.getId());
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        try {
            constructionDrawingReturn = constructionDrawingService.save(constructionDrawing);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
            httpHeaders.setLocation(new URI("/product"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

    /**
     * @param raw_id
     * @return
     */

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteConstructionDrawing(@PathVariable("id") String raw_id) {
        long id;
        try {
            id = Long.parseLong(raw_id);
        } catch (NumberFormatException ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if(id == -1){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
            try {
                constructionDrawingService.softDelete(id);
            } catch (Exception ex) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }


        try {
            httpHeaders.setLocation(new URI("/product"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }


}
