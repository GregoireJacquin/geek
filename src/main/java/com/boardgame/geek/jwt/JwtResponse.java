package com.boardgame.geek.jwt;

import lombok.Data;

@Data
public class JwtResponse {
    private String username;
    public JwtResponse(String username) {
        this.username = username;
    }
}
