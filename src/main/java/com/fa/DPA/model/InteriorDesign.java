package com.fa.DPA.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="interior_design")
@EntityListeners({AuditingEntityListener.class})
public class InteriorDesign extends AbstractModel {
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "content")
    private String content;

    @Column(name = "image_source")
    private String imageSource;

    @Column(name = "create_date")
    @CreatedDate
    private Date createDate;

    @CreatedBy
    @Column(name = "create_by")
    private String createBy;

    @Column
    private String thumbnail;

    @ManyToMany(cascade=CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "idr_Subcategory",
            joinColumns = @JoinColumn(name = "ird_id"),
            inverseJoinColumns = @JoinColumn(name = "subcategory_id"))
    private List<SubCategory> subCategories;



}
