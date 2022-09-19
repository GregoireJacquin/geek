package com.boardgame.geek.game;

import com.boardgame.geek.user.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String title;
    private GameStatus gameStatus;
    @ManyToOne
    private Category category;
    @Transient
    private int categoryId;
    @ManyToOne
    private UserInfo user;
    private boolean deleted;
}
