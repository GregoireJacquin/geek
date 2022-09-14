package com.boardgame.geek.borrow;

import com.boardgame.geek.game.Game;
import com.boardgame.geek.user.User;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@Entity
public class Borrow {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private User borrower;
    @ManyToOne
    private User lender;
    @ManyToOne
    private Game game;
    private LocalDate askDate;
    private LocalDate closeDate;

}
