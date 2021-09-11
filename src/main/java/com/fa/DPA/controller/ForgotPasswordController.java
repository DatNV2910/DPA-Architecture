package com.fa.DPA.controller;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.model.CustomerAccount;
import com.fa.DPA.model.PasswordResetToken;
import com.fa.DPA.service.CustomerAccountService;
import com.fa.DPA.service.ResetPasswordTokenService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sun.mail.smtp.SMTPTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityNotFoundException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/forgot-password")
public class ForgotPasswordController {
    private CustomerAccountService customerAccountService;
    private ResetPasswordTokenService passwordTokenService;
//    private JavaMailSenderImpl mailSender;

    private PasswordEncoder encoder;

    @Autowired
    public ForgotPasswordController(CustomerAccountService customerAccountService,
                                    ResetPasswordTokenService passwordTokenService,
                                    PasswordEncoder encoder) {
        this.customerAccountService = customerAccountService;
        this.passwordTokenService = passwordTokenService;
        this.encoder = encoder;
    }


    /**
     * @param user
     * @param token
     */
    protected void createPasswordResetTokenForUser(CustomerAccount user, String token) {
        PasswordResetToken myToken = new PasswordResetToken();
        myToken.setUser(user);
        myToken.setToken(token);
        passwordTokenService.save(myToken);
    }


    /**
     *  mailgun api
     * @return
     * @throws UnirestException
     */
    public static JsonNode sendComplexMessage(String toEmail, String subject, String content) throws UnirestException {

        byte[] decodedBytes = Base64.getDecoder().decode(Constant.YOUR_DOMAIN_NAME);
        String decodeDomain = new String(decodedBytes);
        System.out.println("Decode Domain: " + decodeDomain);

        byte[] decodedBytes1 = Base64.getDecoder().decode(Constant.API_KEY);
        String decodeKey = new String(decodedBytes1);
        System.out.println("Decodev Key: " + decodeKey);


        HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + decodeDomain + "/messages")
                .basicAuth("api", decodeKey)
                .field("from", "Datnv123@"+decodeDomain)
                .field("to", toEmail)
                .field("subject", subject)
                .field("html", content)
                .asJson();

        return request.getBody();
    }


    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${spring.mail.protocol}")
    private String protocol;


    /**
     * @param toEmail
     * @param url
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     */
    protected void sendEmail(String toEmail, String url) throws UnsupportedEncodingException, MessagingException, UnirestException {
//        mailSender.setHost(host);
//        mailSender.setPort(port);
//        mailSender.setUsername(username);
//        mailSender.setPassword(password);
//
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        helper.setFrom("");
//        helper.setTo(toEmail);

        String subject = "LINK THIẾT LẬP LẠI MẬT KHẨU";

        String content = "<p>Xin chào! ,</p>"
                + "<p>Bạn vừa yêu cầu thiết lập lại mật khẩu</p>"
                + "<p>Vui lòng click vào link bên dưới để đổi mật khẩu:</p>"
                + "<p><a href=\"" + url + "\">Đổi mật khẩu</a></p>"
                + "<br>"
                + "<p>Bỏ qua email này nếu bạn nhớ mật khẩu của mình, "
                + "hoặc bạn chưa thực hiện yêu cầu.</p>"
                + "<br>"
                + "<p>Thân ái.</p>";

//        helper.setSubject(subject);
//
//        helper.setText(content, true);

//        mailSender.send(message);

//        Properties props = System.getProperties();
//        props.put("mail.smtps.host", "smtp.mailgun.org");
//        props.put("mail.smtps.auth", "true");
//
//        Session session = Session.getInstance(props, null);
//        MimeMessage msg = new MimeMessage(session);
//        msg.setFrom(new InternetAddress("YOU@YOUR_DOMAIN_NAME"));
//
//        InternetAddress[] addrs = InternetAddress.parse(toEmail, false);
//        msg.setRecipients(Message.RecipientType.TO, addrs);
//
//        msg.setSubject(subject);
//        msg.setText(content, "utf-8", "html");
//
//
//        SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
//        t.connect(host, username, password);
//        t.sendMessage(msg, msg.getAllRecipients());
//
//        System.out.println("Response: " + t.getLastServerResponse());
//
//        t.close();

         sendComplexMessage(toEmail, subject, content);
    }


    /**
     * @param email
     * @return
     */
    @PostMapping("/send-email")
    public ResponseEntity<CustomerAccount> sendEmailResetPassword(@RequestBody Map<String, String> email) {
        System.out.println(email.get("email"));
        CustomerAccount customerAccount;
        try {
            customerAccount = customerAccountService.findByEmail(email.get("email"));
        } catch (EntityNotFoundException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

//        if (customerAccount == null) {
//
//        }
        System.out.println(customerAccount);
        String token = UUID.randomUUID().toString();
        createPasswordResetTokenForUser(customerAccount, token);
        try {
            sendEmail(customerAccount.getEmail(), Constant.URL_RESET_PASSWORD + "/" + token);
        } catch (MessagingException | UnsupportedEncodingException | UnirestException ex) {
            System.out.println("Error in send mail");
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(customerAccount, HttpStatus.OK);
    }


    /**
     * @param token
     * @return
     */
    @GetMapping("/change-password/{token}")
    public ResponseEntity<Long> getResetPasswordPage(@PathVariable("token") String token) {
        LocalDateTime timeNow = LocalDateTime.now();
        CustomerAccount returnCustomerAccount;

        try {
            returnCustomerAccount = customerAccountService.findByTokenPassword(token, timeNow);
        } catch (EntityNotFoundException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
        }

        return new ResponseEntity<>(returnCustomerAccount.getId(), HttpStatus.OK);
    }

    /**
     * @param newPassword
     * @return
     */
    @PutMapping("/change-password/reset")
    public ResponseEntity<CustomerAccount> resetPassword(@RequestBody Map<String, String> newPassword) {
        String _newPassword = newPassword.get("new-password");
        if (_newPassword == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        Long id;
        try {
            id = Long.parseLong(newPassword.get("id"));
        } catch (NumberFormatException | NullPointerException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        CustomerAccount customerAccount;
        try {
            customerAccount = customerAccountService.findById(id);
        } catch (EntityNotFoundException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        customerAccount.setPassword(encoder.encode(_newPassword));
        CustomerAccount customerAccountReturn = customerAccountService.saveAccount(customerAccount);
        if (customerAccountReturn == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(customerAccountReturn, HttpStatus.OK);
    }
}
