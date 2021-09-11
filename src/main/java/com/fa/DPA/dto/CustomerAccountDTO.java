package com.fa.DPA.dto;

import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

public class CustomerAccountDTO extends AbstractDTO{
    private String account_name;
    private String email;
    private String phone;
    private String address;
    private String avatarSource;
    private LocalDate create_date;
    private String username;

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatarSource() {
        return avatarSource;
    }

    public void setAvatarSource(String avatarSource) {
        this.avatarSource = avatarSource;
    }

    public LocalDate getCreate_date() {
        return create_date;
    }

    public void setCreate_date(LocalDate create_date) {
        this.create_date = create_date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
