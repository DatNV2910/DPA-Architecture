package com.fa.DPA.controller;


import com.fa.DPA.common.JwtUtils;
import com.fa.DPA.dto.JwtResponse;
import com.fa.DPA.dto.LoginRequest;
import com.fa.DPA.dto.ResponseMessage;
import com.fa.DPA.dto.SignUpRequest;
import com.fa.DPA.model.CustomerAccount;
import com.fa.DPA.model.StaffAccount;
import com.fa.DPA.repos.CustomerAccountRepository;
import com.fa.DPA.repos.StaffAccountRepository;
import com.fa.DPA.service.CustomerLoginService.CustomerDetailImpl;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer")
public class CustomerLoginController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    CustomerAccountRepository customerAccountRepository;

    @Autowired
    StaffAccountRepository staffAccountRepository;

    @Autowired
    StaffAccountRepository staff;

    /**
     * @param LoginRequest
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<?> LoginRequest(@Validated @RequestBody LoginRequest LoginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(LoginRequest.getUsername(), LoginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            Object principal = authentication.getPrincipal();
            if(principal instanceof CustomerDetailImpl){
                CustomerDetailImpl customerDetail = (CustomerDetailImpl) authentication.getPrincipal();
                return ResponseEntity.ok(new JwtResponse(jwt, customerDetail.getId(), customerDetail.getUsername(), customerDetail.getEmail(), "ROLE_CUSTOMER"));
            }else if(principal instanceof StaffDetailImpl){
                StaffAccount staff = staffAccountRepository.findStaffAccountByUsername(LoginRequest.getUsername()).get();
                if(staff.getAccount_status().getAccount_status().equals("deactive")){
                    return new ResponseEntity<>(new ResponseMessage(true,"T??i Kho???n n??y ???? b??? kh??a"),HttpStatus.NOT_FOUND);
                }else{
                    StaffDetailImpl staffDetail=(StaffDetailImpl)authentication.getPrincipal();
                    return ResponseEntity.ok(new JwtResponse(jwt, staffDetail.getId(),staffDetail.getUsername(),staffDetail.getEmail(),staffDetail.getRole()));
                }
            }else{
                return new ResponseEntity<>(new ResponseMessage(true,"T??n ????ng nh???p ho???c m???t kh???u kh??ng ch??nh x??c"),HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(true,"T??n ????ng nh???p ho???c m???t kh???u kh??ng ch??nh x??c"),HttpStatus.NOT_FOUND);
        }
    }
    /**
     * @param signUpRequest
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<?> CustomerRegister(@Validated @RequestBody SignUpRequest signUpRequest) {
        if (customerAccountRepository.existsByUsername(signUpRequest.getUsername()) ||
                staff.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage(true,"T??n ????ng nh???p ???? t???n t???i"),HttpStatus.BAD_REQUEST);
        }
        if(signUpRequest.getUsername().trim().length()==0){
            return new ResponseEntity<>(new ResponseMessage(true, "T??n ????ng nh???p kh??ng ???????c b??? tr???ng"), HttpStatus.BAD_REQUEST);
        }
        if (customerAccountRepository.existsByEmail(signUpRequest.getEmail())
        || staff.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage(true,"Email ???? t???n t???i"),HttpStatus.BAD_REQUEST);
        }
        if(signUpRequest.getEmail().trim().length()==0){
            return new ResponseEntity<>(new ResponseMessage(true, "Email kh??ng ???????c b??? tr???ng"), HttpStatus.BAD_REQUEST);
        }
        if(customerAccountRepository.existsByPhone(signUpRequest.getPhoneNumber())
                || staff.existsByPhone(signUpRequest.getPhoneNumber())){
            return new ResponseEntity<>(new ResponseMessage(true,"S??? ??i???n tho???i ???? t???n t???i"),HttpStatus.BAD_REQUEST);
        }
        if(signUpRequest.getPhoneNumber().trim().length()==0){
            return new ResponseEntity<>(new ResponseMessage(true, "S??? ??i???n tho???i kh??ng ???????c b??? tr???ng"), HttpStatus.BAD_REQUEST);
        }
        if(signUpRequest.getAccountName().trim().length()==0){
            return new ResponseEntity<>(new ResponseMessage(true, "T??n kh??ng ???????c b??? tr???ng"), HttpStatus.BAD_REQUEST);
        }
        if(signUpRequest.getPassword().trim().length()==0){
            return new ResponseEntity<>(new ResponseMessage(true, "M???t kh???u kh??ng ???????c b??? tr???ng"), HttpStatus.BAD_REQUEST);
        }
        CustomerAccount customer = new CustomerAccount();
        customer.setUsername(signUpRequest.getUsername());
        customer.setPassword(encoder.encode(signUpRequest.getPassword()));
        customer.setEmail(signUpRequest.getEmail());
        customer.setAccount_name(signUpRequest.getAccountName());
        customer.setPhone(signUpRequest.getPhoneNumber());
        customerAccountRepository.save(customer);
        return new ResponseEntity<>(new ResponseMessage(false,"????ng k?? th??nh c??ng"),HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> LogoutRequest(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();

        if((principal instanceof CustomerDetailImpl) || principal !=null)
        {
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return new ResponseEntity<>(new ResponseMessage(false,"????ng xu???t th??nh c??ng"),HttpStatus.OK);
    }
}
