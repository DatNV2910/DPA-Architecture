package com.fa.DPA.model;



import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "discount")
@EntityListeners({AuditingEntityListener.class})
public class Discount extends AbstractModel {
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private int amount;
    @Column(name = "create_date")
    @CreatedDate
    private Date createDate;
    @CreatedBy
    @Column(name = "create_by")
    private String createBy;



//    @OneToOne(mappedBy = "discount")
//    private ConstructionDrawing constructionDrawing;


//    @OneToOne(mappedBy = "discount")
//    private ConstructionDrawing constructionDrawing;


}
