package com.fa.DPA.dto;

import com.fa.DPA.model.FeedbackReply;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FeedbackReplyDTO extends AbstractDTO{
    private String customerName;
    private String customerCareName;
    private String content;
    private LocalDateTime creatTime;

    public FeedbackReplyDTO(FeedbackReply feedbackReply) {
        this.id = feedbackReply.getId();
        this.customerName = feedbackReply.getCustomer() == null ? "" : feedbackReply.getCustomer().getAccount_name();
        this.customerCareName = feedbackReply.getCustomer_care() == null ? "" : feedbackReply.getCustomer_care().getStaff_name();
        this.content = feedbackReply.getContents();
        this.creatTime = feedbackReply.getCreate_time();
    }
}
