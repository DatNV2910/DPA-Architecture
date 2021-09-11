package com.fa.DPA.NewsService;

import com.fa.DPA.model.News;
import com.fa.DPA.model.StaffAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NewsOutput {
    private Page<News> getAllNewsPaging_UTC01;
    private Page<News> getAllNewsPaging_UTC02;
    private Optional<News> getNewsById_UTC01;
    private Optional<News> getNewsById_UTC02;
    private News saveNews_UTC01;
    private News saveNews_UTC02;
    private News saveNews_UTC03;

    public NewsOutput() {
        List<News> newsList = new ArrayList<>();
        newsList.add(new News());
        getAllNewsPaging_UTC01 = new PageImpl<>(newsList);
        getAllNewsPaging_UTC02 = null;

        News test_news_UTC01 = new News();
        test_news_UTC01.setContent("News content");
        test_news_UTC01.setDescription("News description");
        test_news_UTC01.setIsEnable(true);
        test_news_UTC01.setThumb("thumb");
        test_news_UTC01.setTitle("News title");
        test_news_UTC01.setImgSource("Source img url");
        test_news_UTC01.setStaffAccount(new StaffAccount());
        getNewsById_UTC01 = Optional.of(test_news_UTC01);
        getNewsById_UTC02 = null;

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

        saveNews_UTC03 = null;
    }

    public Page<News> getGetAllNewsPaging_UTC01() {
        return getAllNewsPaging_UTC01;
    }

    public Page<News> getGetAllNewsPaging_UTC02() {
        return getAllNewsPaging_UTC02;
    }

    public Optional<News> getGetNewsById_UTC01() {
        return getNewsById_UTC01;
    }

    public Optional<News> getGetNewsById_UTC02() {
        return getNewsById_UTC02;
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
}
