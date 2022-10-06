package com.boardgame.geek.service.repository;

import com.boardgame.geek.model.entity.Game;
import com.boardgame.geek.model.GameStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game,Integer> {
    List<Game> findByStatusAndUserIdNotAndDeletedFalse(GameStatus status, Integer userId);
    List<Game> findByUserIdAndDeletedFalse(Integer userId);
}
