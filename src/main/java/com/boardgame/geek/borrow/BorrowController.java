package com.boardgame.geek.borrow;

import com.boardgame.geek.game.*;
import com.boardgame.geek.user.UserInfo;
import com.boardgame.geek.user.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class BorrowController {
    @Autowired
    UserInfoRepository userInfoRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    BorrowRepository    borrowRepository;
    @Autowired
    GameRepository      gameRepository;
    @GetMapping(value = "/borrows")
    public ResponseEntity getMyBorrow() {
        List<Borrow> borrows = borrowRepository.findByBorrowerId(GameController.getUserConnectedId());
        return new ResponseEntity(borrows,HttpStatus.OK);
    }
    @PostMapping("/borrows/{gameId}")
    public ResponseEntity addBorrow(@PathVariable("gameId") String gameId){
        Integer userConnectedId = GameController.getUserConnectedId();
        Optional<UserInfo> borrower = userInfoRepository.findById(userConnectedId);
        Optional<Game> game = gameRepository.findById(Integer.valueOf(gameId));
        if(borrower.isPresent() && game.isPresent() && game.get().getGameStatus().equals(GameStatus.FREE)){
            Borrow borrow = new Borrow();
            Game bookEntity = game.get();
            borrow.setGame(bookEntity);
            borrow.setBorrower(borrower.get());
            borrow.setLender(bookEntity.getUser());
            borrow.setAskDate(LocalDate.now());
            borrowRepository.save(borrow);

            bookEntity.setGameStatus(GameStatus.BORROWED);
            gameRepository.save(bookEntity);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/borrows/{borrowId}")
    public ResponseEntity deleteBorrow(@PathVariable("borrowId") String borrowId){
        Optional<Borrow> borrow = borrowRepository.findById(Integer.valueOf(borrowId));
        if(!borrow.isPresent())
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        Borrow borrowEntity = borrow.get();
        borrowEntity.setCloseDate(LocalDate.now());
        borrowRepository.save(borrowEntity);

        Game game = borrowEntity.getGame();
        game.setGameStatus(GameStatus.FREE);
        gameRepository.save(game);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
