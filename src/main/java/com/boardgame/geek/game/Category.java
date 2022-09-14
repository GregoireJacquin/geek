package com.boardgame.geek.game;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@AllArgsConstructor
@Entity
public class Category {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String label;
}
