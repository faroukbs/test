package com.roky.thunderspi.controllers;


import com.roky.thunderspi.dto.ResetPass;
import com.roky.thunderspi.entities.User;
import com.roky.thunderspi.services.UserServiceImpl;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/reset")
public class ForgetPasswordController {
    //@Autowired
   // private JavaMailSender mailSender;

    @Autowired
    private UserServiceImpl userService;



    @PostMapping("/forgot_password")
    public ResponseEntity<?> processForgotPassword(@Param(value = "email") String email) {

        String token = RandomString.make(30);
        System.out.println(email);
        try {
            userService.updateResetPasswordToken(token, email);
            String resetPasswordLink = "http://localhost:4200" + "/reset?token=" + token;


        } catch (Exception ex) {
            System.out.println(ex);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }




    @GetMapping("/reset_password")
    public ResponseEntity<?> showResetPasswordForm(@Param(value = "token") String token) {
        User customer = userService.getByResetPasswordToken(token);


        if (customer == null) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/reset_password", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<?> processResetPassword(@RequestBody ResetPass reset) {
        String token = reset.getToken();
        String password = reset.getPassword();

        User customer = userService.getByResetPasswordToken(token);


        if (customer == null) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            userService.updatePassword(customer, password);


        }

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}

