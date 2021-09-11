package com.fa.DPA.NewsService;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.model.News;
import com.fa.DPA.model.StaffAccount;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class NewsInput {
    private Pageable getAllNewsPaging_UTC01;
    private Pageable getAllNewsPaging_UTC02;
    private Long getNewsById_UTC01;
    private Long getNewsById_UTC02;
    private Long getNewsById_UTC03;
    private News saveNews_UTC01;
    private News saveNews_UTC02;
    private News saveNews_UTC03;
    private News saveNews_UTC04;
    private News saveNews_UTC05;
    private News saveNews_UTC06;
    private News saveNews_UTC07;
    private News saveNews_UTC08;
    private News saveNews_UTC09;
    private News saveNews_UTC10;

    public NewsInput() {
        getAllNewsPaging_UTC01 = PageRequest.of(0, Constant.DEFAULT_PAGE_SIZE);
        getAllNewsPaging_UTC02 = null;

        getNewsById_UTC01 = Long.valueOf(1);
        getNewsById_UTC02 = Long.valueOf(-1);
        getNewsById_UTC03 = null;

        saveNews_UTC01 = new News();
        saveNews_UTC01.setContent("News content");
        saveNews_UTC01.setDescription("News description");
        saveNews_UTC01.setIsEnable(true);
        saveNews_UTC01.setThumb("thumb");
        saveNews_UTC01.setTitle("News title");
        saveNews_UTC01.setImgSource("Source img url");
        saveNews_UTC01.setStaffAccount(new StaffAccount());

        saveNews_UTC02 = new News();
        saveNews_UTC02.setContent("News content");
        saveNews_UTC02.setDescription("News description");
        saveNews_UTC02.setIsEnable(false);
        saveNews_UTC02.setThumb("thumb");
        saveNews_UTC02.setTitle("News title");
        saveNews_UTC02.setImgSource("Source img url");
        saveNews_UTC02.setStaffAccount(new StaffAccount());

        saveNews_UTC03 = new News();
        saveNews_UTC03.setDescription("News description");
        saveNews_UTC03.setIsEnable(true);
        saveNews_UTC03.setThumb("thumb");
        saveNews_UTC03.setTitle("News title");
        saveNews_UTC03.setImgSource("Source img url");
        saveNews_UTC03.setStaffAccount(new StaffAccount());

        saveNews_UTC04 = new News();
        saveNews_UTC04.setContent("News content");
        saveNews_UTC04.setIsEnable(true);
        saveNews_UTC04.setThumb("thumb");
        saveNews_UTC04.setTitle("News title");
        saveNews_UTC04.setImgSource("Source img url");
        saveNews_UTC04.setStaffAccount(new StaffAccount());

        saveNews_UTC05 = new News();
        saveNews_UTC05.setContent("News content");
        saveNews_UTC05.setDescription("News description");
        saveNews_UTC05.setThumb("thumb");
        saveNews_UTC05.setTitle("News title");
        saveNews_UTC05.setImgSource("Source img url");
        saveNews_UTC05.setStaffAccount(new StaffAccount());

        saveNews_UTC06 = new News();
        saveNews_UTC06.setContent("News content");
        saveNews_UTC06.setDescription("News description");
        saveNews_UTC06.setIsEnable(true);
        saveNews_UTC06.setTitle("News title");
        saveNews_UTC06.setImgSource("Source img url");
        saveNews_UTC06.setStaffAccount(new StaffAccount());

        saveNews_UTC07 = new News();
        saveNews_UTC07.setContent("News content");
        saveNews_UTC07.setDescription("News description");
        saveNews_UTC07.setIsEnable(true);
        saveNews_UTC07.setThumb("thumb");
        saveNews_UTC07.setImgSource("Source img url");
        saveNews_UTC07.setStaffAccount(new StaffAccount());

        saveNews_UTC08 = new News();
        saveNews_UTC08.setContent("News content");
        saveNews_UTC08.setDescription("News description");
        saveNews_UTC08.setIsEnable(true);
        saveNews_UTC08.setThumb("thumb");
        saveNews_UTC08.setTitle("News title");
        saveNews_UTC08.setStaffAccount(new StaffAccount());

        saveNews_UTC09 = new News();
        saveNews_UTC09.setContent("News content");
        saveNews_UTC09.setDescription("News description");
        saveNews_UTC09.setIsEnable(true);
        saveNews_UTC09.setThumb("thumb");
        saveNews_UTC09.setTitle("News title");
        saveNews_UTC09.setImgSource("Source img url");

        saveNews_UTC10 = null;
    }

    public Pageable getGetAllNewsPaging_UTC01() {
        return getAllNewsPaging_UTC01;
    }

    public Pageable getGetAllNewsPaging_UTC02() {
        return getAllNewsPaging_UTC02;
    }

    public Long getGetNewsById_UTC01() {
        return getNewsById_UTC01;
    }

    public Long getGetNewsById_UTC02() {
        return getNewsById_UTC02;
    }

    public Long getGetNewsById_UTC03() {
        return getNewsById_UTC03;
    }

    public News getSaveNews_UTC01() {
        return saveNews_UTC01;
    }

    public News getSaveNews_UTC02() {
        return saveNews_UTC02;
    }

    public News getSaveNews_UTC03() {
        return saveNews_UTC03;
    }

    public News getSaveNews_UTC04() {
        return saveNews_UTC04;
    }

    public News getSaveNews_UTC05() {
        return saveNews_UTC05;
    }

    public News getSaveNews_UTC06() {
        return saveNews_UTC06;
    }

    public News getSaveNews_UTC07() {
        return saveNews_UTC07;
    }

    public News getSaveNews_UTC08() {
        return saveNews_UTC08;
    }

    public News getSaveNews_UTC09() {
        return saveNews_UTC09;
    }

    public News getSaveNews_UTC10() {
        return saveNews_UTC10;
    }
}
