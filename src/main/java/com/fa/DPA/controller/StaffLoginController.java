package com.fa.DPA.controller;

import com.fa.DPA.common.JwtUtils;
import com.fa.DPA.dto.JwtResponse;
import com.fa.DPA.dto.LoginRequest;
import com.fa.DPA.dto.ResponseMessage;
import com.fa.DPA.dto.SignUpRequest;
import com.fa.DPA.model.Account_Status;
import com.fa.DPA.model.Role;
import com.fa.DPA.model.StaffAccount;
import com.fa.DPA.repos.CustomerAccountRepository;
import com.fa.DPA.repos.RoleRepository;
import com.fa.DPA.repos.StaffAccountRepository;
import com.fa.DPA.service.StaffLoginService.StaffDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;




@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/api/staff")
public class StaffLoginController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    StaffAccountRepository staffAccountRepository;

    @Autowired
    CustomerAccountRepository customerAccountRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    /**
     *
     * @param staffLoginRequest
     * @return
     */
//    @PostMapping("/login")
//    public ResponseEntity<?> StaffLogin(@Validated @RequestBody LoginRequest staffLoginRequest){
//        try{
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(staffLoginRequest.getUsername(),staffLoginRequest.getPassword()));
//            StaffAccount staff = staffAccountRepository.findStaffAccountByUsername(staffLoginRequest.getUsername()).get();
//            if(staff.getAccount_status().getAccount_status().equals("deactive")){
//                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//            }else{
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//                String jwt = jwtUtils.generateJwtToken(authentication);
//                StaffDetailImpl staffDetail=(StaffDetailImpl)authentication.getPrincipal();
//                return ResponseEntity.ok(new JwtResponse(jwt, staffDetail.getId(),staffDetail.getUsername(),staffDetail.getEmail(),staffDetail.getRole()));
//            }
//        }catch (Exception e){
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }

    /**
     *
     * @param signUpRequest
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<?> registerStaff(@Validated @RequestBody SignUpRequest signUpRequest){
        if(staffAccountRepository.existsByUsername(signUpRequest.getUsername())
        || customerAccountRepository.existsByUsername(signUpRequest.getUsername())){
            return new ResponseEntity<>(new ResponseMessage(true,"Tên đăng nhập đã tồn tại"),HttpStatus.BAD_REQUEST);
        }
        if(staffAccountRepository.existsByEmail(signUpRequest.getEmail())
        || customerAccountRepository.existsByEmail(signUpRequest.getEmail())){
            return new ResponseEntity<>(new ResponseMessage(true,"Email đã tồn tại"),HttpStatus.BAD_REQUEST);
        }
        if(staffAccountRepository.existsByPhone(signUpRequest.getPhoneNumber())
                || customerAccountRepository.existsByPhone(signUpRequest.getPhoneNumber())){
            return new ResponseEntity<>(new ResponseMessage(true,"Số điện thoại đã tồn tại"),HttpStatus.BAD_REQUEST);
        }
        StaffAccount staffAccount = new StaffAccount();
        staffAccount.setUsername(signUpRequest.getUsername());
        staffAccount.setEmail(signUpRequest.getEmail());
        staffAccount.setPassword(encoder.encode(signUpRequest.getPassword()));
        staffAccount.setStaff_name(signUpRequest.getAccountName());
        staffAccount.setPhone(signUpRequest.getPhoneNumber());
        Account_Status status = new Account_Status();
        status.setId((long) 1);
        staffAccount.setAccount_status(status);
        //to change
        Role role =roleRepository.findRoleByName(signUpRequest.getRole()).get();
        staffAccount.setRole(role);
        staffAccountRepository.save(staffAccount);
        return new ResponseEntity<>(new ResponseMessage(false,"Tạo tài khoản thành công"),HttpStatus.OK);
    }

    /**
     *
     * @param
     * @param
     * @return
     */
    @GetMapping("/logout")
    public ResponseEntity<?> StaffLogout(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();

        if((principal instanceof StaffDetailImpl) || principal !=null)
        {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return new ResponseEntity<>(new ResponseMessage(false,"Đăng xuất thành công"),HttpStatus.OK);
    }

}
