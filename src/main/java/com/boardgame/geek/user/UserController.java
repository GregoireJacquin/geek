package com.boardgame.geek.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @PostMapping(value = "/users")
    public ResponseEntity addUser(@Valid @RequestBody User user){
        //TODO persist user
        User localUser = new User();
        localUser.setEmail("greg@local.com");
        return new ResponseEntity(user, HttpStatus.CREATED);
    }
}
