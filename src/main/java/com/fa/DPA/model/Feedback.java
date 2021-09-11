package com.fa.DPA.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@EntityListeners({AuditingEntityListener.class})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback extends AbstractModel{
    @OneToOne
    @JoinColumn(name = "orders_id")
    private Order order;
    @Column(columnDefinition = "VARCHAR(1000) CHARSET utf8")
    private String contents;
    @CreatedDate
    private LocalDate create_date;

}
