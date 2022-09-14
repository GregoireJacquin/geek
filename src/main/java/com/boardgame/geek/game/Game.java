package com.boardgame.geek.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

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
    private User user;
    private boolean deleted;
}
