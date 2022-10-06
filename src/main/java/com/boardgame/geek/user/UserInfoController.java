package com.boardgame.geek.user;

import com.boardgame.geek.jwt.JwtController;
import com.boardgame.geek.jwt.JwtFilter;
import com.boardgame.geek.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.security.Security;
import java.util.List;

@RestController
public class UserInfoController {
    @Autowired
    UserInfoRepository  userInfoRepository;
    @Autowired
    JwtUtils    jwtUtils;
    @Autowired
    JwtController   jwtController;
    @PostMapping(value = "/users")
    public ResponseEntity addUser(@Valid @RequestBody UserInfo user){
        UserInfo existingUser = userInfoRepository.findOneByEmail(user.getEmail());
        if(existingUser != null)
            return new ResponseEntity("User already existing",HttpStatus.BAD_REQUEST);
        UserInfo userSaved = saveUser(user);
        Authentication authentication = jwtController.logUser(user.getEmail(),user.getPassword());
        String jwt = jwtUtils.generateToken(authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORISATION_HEADER,"Bearer " + jwt);
        return new ResponseEntity(userSaved, HttpStatus.CREATED);
    }

    @GetMapping(value = "/isConnected")
    public ResponseEntity getUserConnected(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof Principal) {
            return new ResponseEntity(((UserDetails) principal).getUsername(),HttpStatus.OK);
        }
        return new ResponseEntity("User is not connected",HttpStatus.FORBIDDEN);
    }

    private UserInfo saveUser(UserInfo userInfo) {
        UserInfo user = new UserInfo();
        user.setEmail(userInfo.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(userInfo.getPassword()));
        user.setLastName(StringUtils.capitalize(userInfo.getLastName()));
        user.setFirstName(StringUtils.capitalize(userInfo.getFirstName()));
        userInfoRepository.save(user);
        return user;
    }
}
