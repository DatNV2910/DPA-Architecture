package com.fa.DPA.dto;

import com.fa.DPA.model.StaffAccount;
import lombok.Data;

@Data
public class StaffDetailDTO extends AbstractDTO{
    private String accountName;
    private String userName;
    private String email;
    private String phone;
    private String role;
    private Long numberFinishingOrder;

    public StaffDetailDTO(StaffAccount staffAccount, long numberFinishingOrder) {
        this.id = staffAccount.getId();
        this.accountName = staffAccount.getStaff_name();
        this.userName = staffAccount.getUsername();
        this.email = staffAccount.getEmail();
        this.phone = staffAccount.getPhone();
        this.role = staffAccount.getRole().getName();
        this.numberFinishingOrder = numberFinishingOrder;
    }
}
