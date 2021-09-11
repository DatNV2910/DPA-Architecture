package com.fa.DPA.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "customer_account")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners({AuditingEntityListener.class})
public class CustomerAccount extends BaseAccount{

    @Column(columnDefinition = "VARCHAR(100) CHARSET utf8", nullable = false)
    private String account_name;

    @Column(unique = true, length = 50)
    private String email;

    @Column(length = 30)
    private String phone;

    @Column(length = 50)
    private String address;

    @Column
    private String avatarSource;

    @CreatedDate
    private LocalDate create_date;
}
