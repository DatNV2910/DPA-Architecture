package com.fa.DPA.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetToken extends AbstractModel{

    private String token;

    @OneToOne(targetEntity = CustomerAccount.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private CustomerAccount user;

    private LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(30);
}
