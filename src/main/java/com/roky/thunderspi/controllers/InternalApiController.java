package com.roky.thunderspi.controllers;

import com.roky.thunderspi.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/internal")
@CrossOrigin("*")
public class InternalApiController {

    @Autowired
    private UserServiceImpl userService;

    @PutMapping("/make-admin/{email}")
    public ResponseEntity<?> makeAdmin(@PathVariable String email){
         userService.makeAdmin(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
