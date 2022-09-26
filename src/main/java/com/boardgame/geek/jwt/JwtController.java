package com.boardgame.geek.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import static com.boardgame.geek.jwt.JwtFilter.AUTHORISATION_HEADER;

@RestController
public class JwtController {

    @Autowired
    JwtUtils    jwtUtils;

    @Autowired
    AuthenticationManagerBuilder    authenticationManagerBuilder;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest jwtRequest, HttpServletResponse response) {
        Authentication authentication = logUser(jwtRequest.getEmail(),jwtRequest.getPassword());
        String jwt = jwtUtils.generateToken(authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(AUTHORISATION_HEADER,"Bearer " +jwt);
        Object principal = authentication.getPrincipal();
        return new ResponseEntity<>(new JwtResponse(principal.toString()),httpHeaders, HttpStatus.OK);
    }

    public Authentication logUser(String email, String password) {
        Authentication authentication = authenticationManagerBuilder
                .getObject()
                .authenticate(new UsernamePasswordAuthenticationToken(email,password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

}
