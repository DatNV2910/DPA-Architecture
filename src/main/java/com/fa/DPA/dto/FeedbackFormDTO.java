package com.fa.DPA.dto;

import com.fa.DPA.model.Feedback;
import com.fa.DPA.model.FeedbackReply;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class FeedbackFormDTO extends AbstractDTO{
    private String contentFeedback;
    private LocalDate createDate;
    private String customerName;
    private List<FeedbackReplyDTO> replyDTOList = new ArrayList<>();

    public FeedbackFormDTO(Feedback f, List<FeedbackReply> fl) {
        this.id = f.getId();
        this.contentFeedback = f.getContents();
        this.createDate = f.getCreate_date();
        this.customerName = f.getOrder().getOwner().getAccount_name();
        for (FeedbackReply feedbackReply: fl) {
            FeedbackReplyDTO feedbackReplyDTO = new FeedbackReplyDTO(feedbackReply);
            this.replyDTOList.add(feedbackReplyDTO);
        }
    }
}
