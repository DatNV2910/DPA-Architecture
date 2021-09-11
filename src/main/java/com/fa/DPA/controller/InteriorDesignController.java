package com.fa.DPA.controller;


import com.fa.DPA.constant.Constant;
import com.fa.DPA.controller.output.InteriorOutput;
import com.fa.DPA.dto.InteriorDesignDTO;
import com.fa.DPA.dto.ResponseMessage;
import com.fa.DPA.model.InteriorDesign;
import com.fa.DPA.repos.InteriorDesignRepository;
import com.fa.DPA.service.InteriorDesignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/interior")
public class InteriorDesignController {
    @Autowired
    private InteriorDesignService interiorDesignService;
    @Autowired
    private InteriorDesignRepository repository;

    HttpHeaders httpHeaders = new HttpHeaders();

    /**
     *
     * @param interiorDesign
     * @return
     */
    @PostMapping(value = "/create")
    public ResponseEntity<Object> createInterior(@RequestBody InteriorDesign interiorDesign) {
        if(interiorDesign.getTitle().trim().length()==0){
            return new ResponseEntity<>(new ResponseMessage(true,"Tiêu đề không được bỏ trống!"),HttpStatus.BAD_REQUEST);
        }
        if(interiorDesign.getContent().trim().length()==0){
            return new ResponseEntity<>(new ResponseMessage(true,"Nội dung không được bỏ trống!"),HttpStatus.BAD_REQUEST);
        }
        InteriorDesign interiorDesignReturn = interiorDesignService.save(interiorDesign);

        if (interiorDesignReturn == null) {
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
     * @param interiorDesign
     * @param raw_id
     * @return
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateInterior(@RequestBody InteriorDesign interiorDesign,
                                                         @PathVariable("id") String raw_id) {
        long id = Constant.checkId(raw_id);
        if(id == -1){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if(interiorDesign.getTitle().trim().length()==0){
            return new ResponseEntity<>(new ResponseMessage(true,"Tiêu đề không được bỏ trống!"),HttpStatus.BAD_REQUEST);
        }
        if(interiorDesign.getContent().trim().length()==0){
            return new ResponseEntity<>(new ResponseMessage(true,"Nội dung không được bỏ trống!"),HttpStatus.BAD_REQUEST);
        }
        if(repository.existsById(id)){
            interiorDesign.setId(id);
            interiorDesign = interiorDesignService.save(interiorDesign);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if (interiorDesign == null) {
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
     * @param id
     * @return
     */
    @DeleteMapping(value = "/interior")
    public ResponseEntity<Object> delete(@RequestBody long[] id) {

        try {
            interiorDesignService.delete(id);
        } catch (Exception ex) {
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



    /*@GetMapping(value = "/interior")
    public ResponseEntity<Map<String, Object> > getInteriorPaging(@RequestParam(defaultValue = Constant.DEFAULT_NUM_PAGE) int page){
        Map<String, Object> response = new HashMap<>();
        List<InteriorDesign> interiorDesignList;
        Pageable pageable = PageRequest.of(page, Constant.DEFAULT_PAGE_SIZE);
        try{
            Page<InteriorDesign> interiorDesignPage = interiorDesignService.getALLInteriorPaging(pageable);
            interiorDesignList = interiorDesignPage.getContent();
            response.put("interiorList", interiorDesignList);
            response.put("currentPage", interiorDesignPage.getNumber());
            response.put("totalPage", interiorDesignPage.getTotalPages());
            response.put("totalItem", interiorDesignPage.getTotalElements());

        } catch (Exception ex){
            System.out.println(ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }*/

    /**
     *
     * @param raw_page
     * @return
     */
    @GetMapping(value = "/all")
    public ResponseEntity<InteriorOutput> getAllByPaging(
            @RequestParam(name = "page", defaultValue = Constant.DEFAULT_NUM_PAGE) String raw_page) {
        int page = Constant.checkPage(raw_page);
        if (page == -1) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        InteriorOutput result = new InteriorOutput();
        try {
            result.setPage(page);
            Pageable pageable = PageRequest.of(page, Constant.DEFAULT_PAGE_SIZE_PRODUCT);
            result.setListResut(interiorDesignService.findAll(pageable));
            result.setTotalPage((int) Math.ceil((double) (interiorDesignService.totalItems()) / Constant.DEFAULT_PAGE_SIZE_PRODUCT));
            result.setTotalItems(interiorDesignService.totalItems());
        } catch (Exception ex){
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);


    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<InteriorDesignDTO> getById(@PathVariable("id") String raw_id){
        long id = Constant.checkId(raw_id);
        if(id < 0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        InteriorDesignDTO dto=null;
        try{
            if (repository.existsById(id)) {
                dto = interiorDesignService.getInteriorDesignById(id);
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);


    }

}
