package com.fa.DPA.controller;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.dto.FeedbackDTO;
import com.fa.DPA.dto.FeedbackFormDTO;
import com.fa.DPA.dto.OrderDTO;
import com.fa.DPA.model.*;
import com.fa.DPA.service.CustomerAccountService;
import com.fa.DPA.service.FeedbackService;
import com.fa.DPA.service.StaffAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    private CustomerAccountService customerAccountService;
    private FeedbackService feedbackService;
    private StaffAccountService staffAccountService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService,
                              CustomerAccountService customerAccountService,
                              StaffAccountService staffAccountService) {
        this.feedbackService = feedbackService;
        this.customerAccountService = customerAccountService;
        this.staffAccountService = staffAccountService;
    }

    /**
     * @param page
     * @return
     */
    public ResponseEntity<Map<String, Object>> process(int page) {
        Map<String, Object> response = new HashMap<>();
        List<Feedback> feedbackList;
        List<FeedbackDTO> feedbackDTOS = new ArrayList<>();
        Pageable pageable = PageRequest.of(page, Constant.DEFAULT_PAGE_SIZE);
        Page<Feedback> feedbackPage;
        try {
            feedbackPage = feedbackService.getAllFeedback(pageable);
            feedbackList = feedbackPage.getContent();
            System.out.println(feedbackList);
            for (Feedback f : feedbackList) {
                FeedbackDTO feedbackDTO = new FeedbackDTO(f);
                feedbackDTOS.add(feedbackDTO);
            }
            response.put("feedbackList", feedbackDTOS);
            response.put("currentPage", feedbackPage.getNumber());
            response.put("totalPage", feedbackPage.getTotalPages());
            response.put("totalItem", feedbackPage.getTotalElements());
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /**
     *
     * @param raw_page
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllFeedback(
            @RequestParam(name = "page",defaultValue = Constant.DEFAULT_NUM_PAGE) String raw_page) {
        int page = Constant.checkPage(raw_page);
        if (page == -1) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return process(page);
    }

    /**
     *
     * @param raw_idFeedback
     * @return
     */
    @GetMapping("/detail/{idFeedback}")
    @ExceptionHandler(Exception.class)
    public ResponseEntity<FeedbackFormDTO> getDetailReply(@PathVariable("idFeedback") String raw_idFeedback) {
        long idFeedback = Constant.checkId(raw_idFeedback);
        if (idFeedback < 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Feedback feedback;
        try {
            feedback = feedbackService.findFeedbackById(idFeedback);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        List<FeedbackReply> feedbackReplies = feedbackService.findListReplyByFeedbackID(idFeedback);
        if (feedbackReplies == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        FeedbackFormDTO feedbackFormDTO = new FeedbackFormDTO(feedback, feedbackReplies);

        return new ResponseEntity<>(feedbackFormDTO, HttpStatus.OK);
    }


    HttpHeaders httpHeaders = new HttpHeaders();


    /**
     * @param resp
     * @return
     */
    @PostMapping("/reply")
    public ResponseEntity<Object> replyFeedback(@RequestBody Map<String, String> resp) {
        FeedbackReply feedbackReply = new FeedbackReply();
        Feedback feedback = new Feedback();
        System.out.println(resp.get("idHuman"));
        Long id, idFeedback;
        try {
            id = Long.parseLong(resp.get("idHuman"));
            idFeedback = Long.parseLong(resp.get("idFeedback"));
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>("Error when parsing id", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        System.out.println(id + "-" + idFeedback);
        feedback.setId(idFeedback);

        CustomerAccount customerAccount = null;
        StaffAccount staffAccount = null;
        String type = resp.get("type");
        switch (type) {
            case Constant.ROLE_CUS:
                try {
                    customerAccount = customerAccountService.findById(id);

                } catch (EntityNotFoundException ex) {

                    ex.printStackTrace();
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }
                break;
            case Constant.ROLE_CUS_CARE:
                try {
                    staffAccount = staffAccountService.findById(id);
                } catch (EntityNotFoundException ex) {
                    ex.printStackTrace();
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                }
                break;
        }



        String content = resp.get("content");

        feedbackReply.setFeedback(feedback);
        feedbackReply.setCustomer(customerAccount);
        feedbackReply.setCustomer_care(staffAccount);
        feedbackReply.setContents(content);


        FeedbackReply feedbackReplyReturn = feedbackService.save(feedbackReply);

        if (feedbackReplyReturn == null) {
            return new ResponseEntity<>("Can't save reply", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            httpHeaders.setLocation(new URI("/feedback/detail/" + idFeedback));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);

    }
    @PostMapping
    public ResponseEntity<Feedback> sendFeedback(@RequestBody Feedback feedback){
        Feedback feedbackReturn = feedbackService.saveFeedback(feedback);
        if(feedbackReturn == null){
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



