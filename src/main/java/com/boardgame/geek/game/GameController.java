package com.boardgame.geek.game;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

@RestController
public class GameController {
    @GetMapping(value = "/games")
    public ResponseEntity listGames(){
        Game game = new Game();
        game.setTitle("Northgard");
        game.setCategory(new Category("4X"));
        return new ResponseEntity(Arrays.asList(game), HttpStatus.OK);
    }

    @PostMapping(value = "/games")
    public ResponseEntity addGame(@RequestBody Game game){
        //TODO persist
        return new ResponseEntity(game,HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/games/{gameId}")
    public ResponseEntity deleteGame(@PathVariable("gameId") String gameId){
        //TODO delete game id
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/games/{bookId}")
    public ResponseEntity updateGame(@PathVariable("gameId") String gameId,@Valid @RequestBody Game game){
        //TODO update game
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("/categories")
    public ResponseEntity listCatgories() {
        Category categories = new Category("DeckBuilding");
        Category categories2 = new Category("Draft");
    return new ResponseEntity(Arrays.asList(categories,categories2),HttpStatus.OK);
    }
}