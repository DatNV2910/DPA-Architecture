package com.fa.DPA.service;

import com.fa.DPA.model.News;
import com.fa.DPA.model.StaffAccount;
import com.fa.DPA.repos.NewsRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class NewsService {
    private NewsRepository newsRepository;

    /**
     * param newsRepository
     */
    @Autowired
    public NewsService(NewsRepository newsRepository){
        this.newsRepository=newsRepository;
    }

    public Page<News> getAllNewsPaging(Pageable pageable, boolean isStaff){
        try {
            if(isStaff){
                return newsRepository.findAll(pageable);
            }else{
                return newsRepository.findAllwithstatusTrue(pageable);
            }
        }catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }
    public Optional<News> getNewsById(Long id){
        try {
            return newsRepository.findById(id);
        }catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }

    public News saveNews(News news){
        try {
            return newsRepository.save(news);
        }catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }
}
