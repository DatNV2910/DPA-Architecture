package com.fa.DPA.repos;

import com.fa.DPA.model.Feedback;
import com.fa.DPA.model.FeedbackReply;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackReplyRepository extends BaseRepository<FeedbackReply>{

    /**
     *
     * @param f
     * @return
     */
    List<FeedbackReply> findAllByFeedback(Feedback f);
}
