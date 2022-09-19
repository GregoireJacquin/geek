package com.boardgame.geek.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Size(min = 5 ,max = 25,message = "Le nom doit faire entre 5- 25 caracteres ")
    private String firstName;
    @Size(min = 5 ,max = 25,message = "Le nom doit faire entre 5- 25 caracteres ")
    private String lastName;
    private String email;
    private String password;
    @Transient
    private String token;
}
