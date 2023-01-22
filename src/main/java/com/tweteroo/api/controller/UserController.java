package com.tweteroo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweteroo.api.dto.UserDTO;
import com.tweteroo.api.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService service;

    @CrossOrigin
    @PostMapping("/sign-up")
    public void createUser(@RequestBody UserDTO req) {
        service.createUser(req);
    }
}
