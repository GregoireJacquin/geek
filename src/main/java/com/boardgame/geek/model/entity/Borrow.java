package com.boardgame.geek.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Borrow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private UserInfo borrower;
    @ManyToOne
    private UserInfo lender;
    @ManyToOne
    private Game game;
    private LocalDate askDate;
    private LocalDate closeDate;

}
