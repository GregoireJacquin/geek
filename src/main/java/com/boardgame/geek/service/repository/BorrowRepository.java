package com.boardgame.geek.service.repository;

import com.boardgame.geek.model.entity.Borrow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends CrudRepository<Borrow,Integer> {
    List<Borrow> findByBorrowerId(Integer borrowerId);
    List<Borrow> findByGameId(Integer gameId);
}
