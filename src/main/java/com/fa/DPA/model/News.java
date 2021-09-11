package com.fa.DPA.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "News")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class News extends AbstractModel{
    @Column(columnDefinition = "VARCHAR(255) CHARSET utf8",nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT CHARSET utf8",nullable = false)
    private String description;

    @Column(columnDefinition = "TEXT CHARSET utf8",nullable = false)
    private String content;

    @Column(name = "created_date",nullable = false)
    @CreatedDate
    private Date createdDate;

    @Column
    private String imgSource;

    @ManyToOne
    @JoinColumn(name = "create_by", nullable = false)
    private StaffAccount staffAccount;

    @Column(name="is_Enable")
    private Boolean isEnable;

    @Column
    private String thumb;

}
