package com.singletonapps.controller;

import com.singletonapps.model.Game;
import com.singletonapps.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by David Cuellar on 8/06/17.
 */
@RestController
@RequestMapping("v1")
public class GamesController {

    @Autowired
    private GameService gameService;


    @PostMapping("games")
    public ResponseEntity<Game> createGame(@RequestBody Game game){

        Game gameCreated = gameService.createGame(game);

        return new ResponseEntity<>(gameCreated, HttpStatus.CREATED);
    }


}
