package com.fa.DPA.service;

import com.fa.DPA.model.Feedback;
import com.fa.DPA.model.FeedbackReply;
import com.fa.DPA.repos.FeedbackReplyRepository;
import com.fa.DPA.repos.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class FeedbackService {
    private FeedbackRepository feedbackRepository;
    private FeedbackReplyRepository feedbackReplyRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository, FeedbackReplyRepository feedbackReplyRepository) {
        this.feedbackReplyRepository = feedbackReplyRepository;
        this.feedbackRepository = feedbackRepository;
    }

    /**
     *
     * @param pageable
     * @return
     */
    public Page<Feedback> getAllFeedback(Pageable pageable){
        try{
            return feedbackRepository.findAll(pageable);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    public Feedback findFeedbackById(Long id){
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Data with this id: " + id + " is not found"));
    }

    /**
     *
     * @param id
     * @return
     */
    public FeedbackReply findFeedbackReplyById(Long id){
        return feedbackReplyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Data with this id: " + id + " is not found"));
    }

    public FeedbackReply save(FeedbackReply feedbackReply){
        try{
            return feedbackReplyRepository.save(feedbackReply);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    public List<FeedbackReply> findListReplyByFeedbackID(Long id){
        Feedback feedback = new Feedback();
        feedback.setId(id);
        try{
            return feedbackReplyRepository.findAllByFeedback(feedback);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public Feedback saveFeedback(Feedback feedback){
        try {
            feedbackRepository.save(feedback);
            return feedback;
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }



}
