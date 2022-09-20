package com.boardgame.geek.game;

import com.boardgame.geek.borrow.Borrow;
import com.boardgame.geek.borrow.BorrowRepository;
import com.boardgame.geek.user.UserInfo;
import com.boardgame.geek.user.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class GameController {
    @Autowired
    GameRepository  gameRepository;
    @Autowired
    UserInfoRepository  userInfoRepository;
    @Autowired
    CategoryRepository  categoryRepository;
    @Autowired
    BorrowRepository    borrowRepository;
    @GetMapping(value = "/games")
    public ResponseEntity listGames(@RequestParam(required = false) GameStatus status){
        Integer userConnectedId = this.getUserConnectedId();
        List<Game> games;
        if(status != null && status == GameStatus.FREE)
            games = gameRepository.findByStatusAndUserIdNotAndDeletedFalse(status,userConnectedId);
        else
            games = gameRepository.findByUserIdAndDeletedFalse(userConnectedId);
        return new ResponseEntity(Arrays.asList(games), HttpStatus.OK);
    }
    @PostMapping(value = "/games")
    public ResponseEntity addGame(@RequestBody @Valid Game game){
        Integer userConnectedId = this.getUserConnectedId();
        Optional<UserInfo> user = userInfoRepository.findById(userConnectedId);
        Optional<Category> category = categoryRepository.findById(game.getCategoryId());
        if(category.isPresent()){
            game.setCategory(category.get());
        } else {
            return new ResponseEntity("You must provide a valide category",HttpStatus.BAD_REQUEST);
        }
        if(user.isPresent())
            game.setUser(user.get());
        else {
            return new ResponseEntity("You must provide a valide user",HttpStatus.BAD_REQUEST);
        }
        game.setDeleted(false);
        game.setGameStatus(GameStatus.FREE);
        gameRepository.save(game);
        return new ResponseEntity(game,HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/games/{gameId}")
    public ResponseEntity deleteGame(@PathVariable("gameId") String gameId){
        Optional<Game> gameToDelete = gameRepository.findById(Integer.valueOf(gameId));
        if(!gameToDelete.isPresent())
            return new ResponseEntity("Game not found",HttpStatus.BAD_REQUEST);
        Game game = gameToDelete.get();
        List<Borrow> borrowList = borrowRepository.findByGameId(game.getId());
        for(Borrow borrow : borrowList) {
            if (borrow.getCloseDate() == null) {
                UserInfo borrewer = borrow.getBorrower();
                return new ResponseEntity(borrewer, HttpStatus.CONFLICT);
            }
        }
        game.setDeleted(true);
        gameRepository.save(game);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @PutMapping(value = "/games/{bookId}")
    public ResponseEntity updateGame(@PathVariable("gameId") String gameId,@Valid @RequestBody Game game){
        Optional<Game> gameToUpdate = gameRepository.findById(Integer.valueOf(gameId));
        if(!gameToUpdate.isPresent()){
            return new ResponseEntity("Game not existing",HttpStatus.BAD_REQUEST);
        }
        Game gameToSave = gameToUpdate.get();
        Optional<Category> newCategory = categoryRepository.findById(game.getCategoryId());
        gameToSave.setCategory(newCategory.get());
        gameToSave.setTitle(game.getTitle());
        gameRepository.save(gameToSave);
        return new ResponseEntity(gameToSave,HttpStatus.OK);
    }
    @GetMapping("/categories")
    public ResponseEntity listCatgories() {
        return new ResponseEntity(categoryRepository.findAll(),HttpStatus.OK);
    }
    @GetMapping(value = "/games/{gameId}")
    public ResponseEntity loadBook(@PathVariable("bookId") String bookId){
        Optional<Game> game = gameRepository.findById(Integer.valueOf(bookId));
        if(!game.isPresent())
            return new ResponseEntity("Book not found", HttpStatus.BAD_REQUEST);
        return new ResponseEntity(game.get(),HttpStatus.OK);
    }
    public static Integer getUserConnectedId() {
        //TODO Coding the party security
        return 1;
    }
}
