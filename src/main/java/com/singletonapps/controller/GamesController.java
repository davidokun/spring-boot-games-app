package com.singletonapps.controller;

import com.singletonapps.model.Game;
import com.singletonapps.model.GamesResponse;
import com.singletonapps.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("games")
    public ResponseEntity<GamesResponse> getAllGames(){

        GamesResponse gamesResponse = gameService.getAllGames();

        return new ResponseEntity<>(gamesResponse, HttpStatus.OK);
    }


}
