package com.fa.DPA.service;

import com.fa.DPA.model.PasswordResetToken;
import com.fa.DPA.repos.PasswordTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResetPasswordTokenService {
    private PasswordTokenRepository passwordTokenRepository;

    @Autowired
    public ResetPasswordTokenService(PasswordTokenRepository passwordTokenRepository) {
        this.passwordTokenRepository = passwordTokenRepository;
    }

    /**
     *
     * @param passwordResetToken
     * @return
     */
    public PasswordResetToken save(PasswordResetToken passwordResetToken){
        try{
            PasswordResetToken returnPass = passwordTokenRepository.save(passwordResetToken);
            System.out.println(returnPass.toString());
            return returnPass;
        }catch (Exception ex){
            System.out.println("Error in create password token");
            System.out.println(ex);
        }
        return null;
    }
}
