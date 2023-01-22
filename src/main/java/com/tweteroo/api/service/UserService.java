package com.tweteroo.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.tweteroo.api.dto.UserDTO;
import com.tweteroo.api.model.Users;
import com.tweteroo.api.repository.UsersRepository;

@Service
public class UserService {

    @Autowired
    private UsersRepository repository;

    public void createUser(@RequestBody UserDTO req) {
        repository.save(new Users(req));
    }
}
