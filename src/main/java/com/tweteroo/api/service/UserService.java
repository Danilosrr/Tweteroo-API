package com.tweteroo.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.tweteroo.api.dto.UserDTO;
import com.tweteroo.api.model.Users;
import com.tweteroo.api.repository.UsersRepository;

@Service
public class UserService {

    @Autowired
    private UsersRepository repository;

    public ResponseEntity<String> createUser(@RequestBody UserDTO req) {
        Users user = repository.findFirstByUsername(req.username());
        if (user != null)
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário existe ou houve problema ao criar conta!");
        else {
            repository.save(new Users(req));
            return ResponseEntity.status(HttpStatus.OK).body("OK");
        }
    }
}
