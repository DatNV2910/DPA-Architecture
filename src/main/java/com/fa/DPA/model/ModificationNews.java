package com.fa.DPA.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Modification_News")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModificationNews extends AbstractModel{
    @ManyToOne
    @JoinColumn(name = "news_id", nullable = false)
    private News news;

    @ManyToOne
    @JoinColumn(name = "modify_by", nullable = false)
    private StaffAccount staffAccount;

    @Column(name = "modify_date")
    private Date modifyDate;
}
