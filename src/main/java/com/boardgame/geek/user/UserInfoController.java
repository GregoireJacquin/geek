package com.boardgame.geek.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.soap.SOAPBinding;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserInfoController {
    @Autowired
    UserInfoRepository  userInfoRepository;
    @PostMapping(value = "/users")
    public ResponseEntity addUser(@Valid @RequestBody UserInfo user){
        List<UserInfo> users = userInfoRepository.findByEmail(user.getEmail());
        if(!users.isEmpty())
            return new ResponseEntity("User already existing",HttpStatus.BAD_REQUEST);
        userInfoRepository.save(user);

        return new ResponseEntity(user, HttpStatus.CREATED);
    }
}
