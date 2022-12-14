package com.boardgame.geek.controller;

import com.boardgame.geek.model.entity.UserInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @PostMapping(value = "/users")
    public ResponseEntity addUser(@Valid @RequestBody UserInfo user){
        //TODO persist user
        UserInfo localUser = new UserInfo();
        localUser.setEmail("greg@local.com");
        return new ResponseEntity(user, HttpStatus.CREATED);
    }
}
