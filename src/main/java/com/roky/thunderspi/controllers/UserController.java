package com.roky.thunderspi.controllers;

import com.roky.thunderspi.entities.User;
import com.roky.thunderspi.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {


    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<User> UpdateUser(@RequestBody User user) throws Exception {
        User Suser = userService.update(user);
        return new ResponseEntity<>(Suser, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<?> processRegister(@RequestBody User user, HttpServletRequest request)
            throws UnsupportedEncodingException, MessagingException {
        if (userService.findByEmail(user.getEmail()).isPresent()){

            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        userService.register(user, getSiteURL(request));
        return new ResponseEntity<>( HttpStatus.OK);
    }


    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
      String a = siteURL.replace(request.getServletPath(), "");
        System.out.println(a);
        return siteURL.replace(request.getServletPath(), "");
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable long id) {

        userService.delete(id);
    }
}
