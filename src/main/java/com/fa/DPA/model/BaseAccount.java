package com.fa.DPA.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseAccount extends AbstractModel {
    @Column(unique = true, nullable = false)
    @JsonProperty("username")
    private String username;
    @Column(nullable = false, columnDefinition = "TEXT")
    @JsonProperty("password")
    private String password;
}
