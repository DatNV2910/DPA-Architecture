package com.fa.DPA.controller;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.dto.NewsRequest;
import com.fa.DPA.dto.ResponseMessage;
import com.fa.DPA.model.ModificationNews;
import com.fa.DPA.model.News;
import com.fa.DPA.model.StaffAccount;
import com.fa.DPA.repos.ModifyNewsRepository;
import com.fa.DPA.repos.StaffAccountRepository;
import com.fa.DPA.service.NewsService;
import com.fa.DPA.service.StaffAccountService;
import com.fa.DPA.service.StaffLoginService.StaffDetailImpl;
import io.swagger.annotations.ApiOperation;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/news")
public class NewsController {
    private NewsService newsService;

    @Autowired
    StaffAccountService staffAccountService;

    @Autowired
    StaffAccountRepository staffAccountRepository;

    @Autowired
    ModifyNewsRepository modifyNewsRepository;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    /**
     *
     * @param raw_page
     * @return
     */
    @GetMapping("/all")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Map<String, Object>> getNewsPage(
            @RequestParam(name = "page",defaultValue = Constant.DEFAULT_NUM_PAGE) String raw_page
    ) {
        int page = Constant.checkPage(raw_page);
        if (page == -1) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        StaffAccount staffAccount = Constant.getStaff(staffAccountService);
        boolean isStaff = false;
        if(staffAccount!=null){
            isStaff = true;
        }
        Map<String, Object> response = new HashMap<>();
        List<News> newsList;
        Pageable pageable = PageRequest.of(page, Constant.DEFAULT_PAGE_SIZE_PRODUCT);
        try {
            Page<News> newsPaging = newsService.getAllNewsPaging(pageable,isStaff);
            newsList = newsPaging.getContent();
            response.put("newsList", newsList);
            response.put("currentPage", newsPaging.getNumber());
            response.put("totalPage", newsPaging.getTotalPages());
            response.put("totalItem", newsPaging.getTotalElements());
        } catch (Exception ex) {
            System.out.println(ex);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/top3")
    public ResponseEntity<Map<String, Object>> gettop3(
            @RequestParam(name = "page",defaultValue = Constant.DEFAULT_NUM_PAGE) String raw_page
    ) {
        StaffAccount staffAccount = Constant.getStaff(staffAccountService);
        boolean isStaff = false;
        if(staffAccount!=null){
            isStaff = true;
        }

        int page = Constant.checkPage(raw_page);
        if (page == -1) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        Map<String, Object> response = new HashMap<>();
        List<News> newsList;
        Pageable pageable = PageRequest.of(page, 3);
        try {
            Page<News> newsPaging = newsService.getAllNewsPaging(pageable,isStaff);
            newsList = newsPaging.getContent();
            response.put("newsList", newsList);
            response.put("currentPage", newsPaging.getNumber());
            response.put("totalPage", newsPaging.getTotalPages());
            response.put("totalItem", newsPaging.getTotalElements());
        } catch (Exception ex) {
            System.out.println(ex);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     *
     * @param raw_id
     * @return
     */
    @GetMapping(value = "/{id}")
    //@PreAuthorize("hasRole('ROLE_SALE') or hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<News> getNewsById(@PathVariable("id") String raw_id) {
        long id = Constant.checkId(raw_id);
        if (id < 0) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Optional<News> findNews = newsService.getNewsById(id);
        if (findNews.isPresent()) {
            return new ResponseEntity<>(findNews.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /**
     *
     * @param newsRequest
     * @return
     */
    //create News
    @PostMapping
   /* @PreAuthorize("hasRole('ROLE_ADMIN')")*/
    public ResponseEntity<?> createNews(@RequestBody NewsRequest newsRequest) {

        if(newsRequest.getTitle().trim().length()==0){
            return new ResponseEntity<>(new ResponseMessage(true,"Tiêu đề không được bỏ trống!"),HttpStatus.BAD_REQUEST);
        }


        try {
            StaffAccount admin = this.getStaff();
            News news = new News();
            news.setStaffAccount(admin);
            news.setTitle(newsRequest.getTitle());
            news.setDescription(newsRequest.getDescription());
            news.setContent(newsRequest.getContent());
            LocalDate create = LocalDate.now();
            news.setCreatedDate(Date.valueOf(create));
            news.setIsEnable(true);
            news.setImgSource(newsRequest.getImgSource());
            news.setThumb(newsRequest.getThumb());
            if(news.getStaffAccount()==null){
                System.out.println("Null required property");
                return new ResponseEntity<>(new ResponseMessage(true,"BAD_REQUEST"),HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(newsService.saveNews(news), HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex);
            return new ResponseEntity<>(new ResponseMessage(true,"BAD_REQUEST"),HttpStatus.BAD_REQUEST);
        }
    }

    /**
     *
     * @param raw_id
     * @param newsRequest
     * @return
     */
    @PutMapping("/{id}")
    /*@PreAuthorize("hasRole('ROLE_ADMIN')")*/
    public ResponseEntity<?> updateNews(@PathVariable("id") String raw_id, @RequestBody NewsRequest newsRequest) {
        long id = Constant.checkId(raw_id);
        if (id < 0) {
            return new ResponseEntity<>(new ResponseMessage(true,"BAD_REQUEST"),HttpStatus.BAD_REQUEST);
        }
        if(newsRequest.getTitle().trim().length()==0){
            return new ResponseEntity<>(new ResponseMessage(true,"Tiêu đề không được bỏ trống!"),HttpStatus.BAD_REQUEST);
        }
        if(newsRequest.getEnable()==null){
            return new ResponseEntity<>(new ResponseMessage(true,"Phải set enable cho bài viết!"),HttpStatus.BAD_REQUEST);
        }
        Optional<News> newsData = newsService.getNewsById(id);
        if (newsData.isPresent()) {
            StaffAccount admin = this.getStaff();
            News _news = newsData.get();
            _news.setTitle(newsRequest.getTitle());
            _news.setContent(newsRequest.getContent());
            _news.setDescription(newsRequest.getDescription());
            _news.setIsEnable(newsRequest.getEnable());
            _news.setThumb(newsRequest.getThumb());
            if(admin==null){
                return new ResponseEntity<>(new ResponseMessage(true,"BAD_REQUEST"),HttpStatus.BAD_REQUEST);
            }
            ModificationNews modify = new ModificationNews();
            LocalDate modifyDate = LocalDate.now();
            modify.setModifyDate(Date.valueOf(modifyDate));
            modify.setNews(_news);
            modify.setStaffAccount(admin);
            modifyNewsRepository.save(modify);
            return new ResponseEntity<>(newsService.saveNews(_news), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage(true,"Not Found"),HttpStatus.NOT_FOUND);
        }
    }

    /**
     *
     * @return
     */
    public StaffAccount getStaff() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        StaffAccount staff = null;
        if (principal instanceof StaffDetailImpl) {
            String username = ((StaffDetailImpl) principal).getUsername();
            staff = staffAccountRepository.findStaffAccountByUsername(username).get();
        }
        return staff;
    }

}
