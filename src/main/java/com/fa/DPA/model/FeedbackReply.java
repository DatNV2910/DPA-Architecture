package com.fa.DPA.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EntityListeners({AuditingEntityListener.class})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackReply extends AbstractModel{
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerAccount customer;

    @Column(columnDefinition = "VARCHAR(1000) CHARSET utf8")
    private String contents;

    @OneToOne
    @JoinColumn(name = "feedback_id")
    private Feedback feedback;

    @OneToOne
    @JoinColumn(name = "customer_care_id")
    private StaffAccount customer_care;
    
    @CreatedDate
    private LocalDateTime create_time;

    private boolean is_staff;
}
