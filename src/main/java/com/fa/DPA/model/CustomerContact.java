package com.fa.DPA.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerContact extends AbstractModel {
    @Column(columnDefinition = "VARCHAR(100) CHARSET utf8")
    private String customer_name;
    @Column(length = 50)
    private String phone;
    @Column(columnDefinition = "VARCHAR(100) CHARSET utf8")
    private String area_size;
    @Column(columnDefinition = "VARCHAR(100) CHARSET utf8")
    private String wanted_style;
    @Column(columnDefinition = "VARCHAR(100) CHARSET utf8")
    private String address;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
}
