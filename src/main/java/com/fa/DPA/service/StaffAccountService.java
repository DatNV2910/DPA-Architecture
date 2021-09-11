package com.fa.DPA.service;

import com.fa.DPA.converter.StaffSelect;
import com.fa.DPA.model.Account_Status;
import com.fa.DPA.model.Order;
import com.fa.DPA.model.Role;
import com.fa.DPA.model.StaffAccount;
import com.fa.DPA.repos.RoleRepository;
import com.fa.DPA.repos.StaffAccountRepository;
import com.fa.DPA.repos.StatusStaffAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StaffAccountService {
    private StaffAccountRepository staffAccountRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private StatusStaffAccount statusStaffAccount;

    /**
     * @param staffAccountRepository
     */
    @Autowired
    public StaffAccountService(StaffAccountRepository staffAccountRepository
    ) {
        this.staffAccountRepository = staffAccountRepository;
    }

    /**
     * @param id
     * @return
     */
    public StaffAccount findById(Long id) {
        return staffAccountRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Data with this id: " + id + " is not found"));
    }

    /**
     *
     * @param staffAccount
     * @return
     */
     public StaffAccount createAccount(StaffAccount staffAccount){
         try{
             staffAccountRepository.save(staffAccount);
             return staffAccount;
         }catch (Exception ex){
             System.out.println(ex);
         }

        return null;
     }

    /**
     * @param pageable
     * @return
     */
    public Page<StaffAccount> getAllStaffPaging(Pageable pageable) {
        try {
            return staffAccountRepository.findAll(pageable);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    /**
     * @param id
     * @param idRole
     */
    public void modifyRoleOrStatus(Long id, Long idRole) throws EntityNotFoundException{
        StaffAccount staffAccount;
        staffAccount = this.findById(id);
        System.out.println(staffAccount.toString());
        if (idRole == null) {
            if (staffAccount.getAccount_status().getAccount_status().equalsIgnoreCase("active")) {
                Account_Status account_status = new Account_Status();
                account_status.setId((long) 2);
                staffAccount.setAccount_status(account_status);
            }else{
                if (staffAccount.getAccount_status().getAccount_status().equals("deactive")) {
                    Account_Status account_status = new Account_Status();
                    account_status.setId((long) 1);
                    staffAccount.setAccount_status(account_status);
                }
            }
        } else {
            if(staffAccount.getRole().getName().equals("ROLE_SALE")){
                Role r = roleRepository.findRoleByName("ROLE_CUSTOMERCARE").get();
                staffAccount.setRole(r);
            }else {
                Role r = roleRepository.findRoleByName("ROLE_SALE").get();
                staffAccount.setRole(r);
            }
        }
        staffAccountRepository.save(staffAccount);
    }

    /**
     *
     * @param id
     * @return
     */
    public int checkExistByID(Long id){
        try{
            return staffAccountRepository.existsStaffAccountById(id) == true ? 1 : 0;
        }catch (Exception ex){
            System.out.println(ex);
        }
        return -1;
    }


    public StaffAccount findStaffAccountByUsername(String username) {
        return staffAccountRepository.findStaffAccountByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Data with this username: " + username + " is not found"));
    }
    /**
     *
     * @param staffAccount
     * @return
     */
    public StaffAccount save(StaffAccount staffAccount){
        try{
            return staffAccountRepository.save(staffAccount);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    /**
     *
     * @param roleId
     * @return
     */
    public List<StaffSelect> selectAllWithRole(long roleId){
        try{
            return staffAccountRepository.findAllWithRole(roleId);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
