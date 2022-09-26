package com.boardgame.geek.jwt;

import lombok.Data;

@Data
public class JwtRequest {
    private String email;
    private String password;
}
