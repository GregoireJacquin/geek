package com.boardgame.geek.game;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game,Integer> {
    List<Game> findByStatusAndUserIdNotAndDeletedFalse(GameStatus status, Integer userId);
    List<Game> findByUserIdAndDeletedFalse(Integer userId);
}
