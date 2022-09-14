package com.boardgame.geek.borrow;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class BorrowController {

    @GetMapping(value = "/borrows")
    public ResponseEntity getMyBorrow() {
        //TODO
        Borrow borrow = new Borrow();
        borrow.setAskDate(LocalDate.now());
        return new ResponseEntity(borrow,HttpStatus.OK);
    }
    @PostMapping("/borrows/{gameId}")
    public ResponseEntity addBorrow(@PathVariable("gameId") String gameId){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/borrows/{borrowId}")
    public ResponseEntity deleteBorrow(@PathVariable("borrowId") String borrowId){
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
