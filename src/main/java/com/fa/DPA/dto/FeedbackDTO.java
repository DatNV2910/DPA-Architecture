package com.fa.DPA.dto;

import com.fa.DPA.model.Feedback;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FeedbackDTO extends AbstractDTO{
    private String orderCode;
    private LocalDate finishOrderDate;
    private LocalDate createDate;
    private String content;
    private long idOrder;

    public FeedbackDTO(Feedback feedback) {
        this.id = feedback.getId();
        this.content = feedback.getContents();
        this.finishOrderDate = feedback.getOrder().getFinishedDate().toLocalDate();
        this.createDate = feedback.getCreate_date();
        this.orderCode = feedback.getOrder().getOrderCode();
        this.idOrder = feedback.getOrder().getId();
    }
}
