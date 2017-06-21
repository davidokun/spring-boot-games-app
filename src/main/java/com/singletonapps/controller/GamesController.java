package com.singletonapps.controller;

import com.singletonapps.model.Game;
import com.singletonapps.model.GamesResponse;
import com.singletonapps.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;


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

    @PutMapping("games/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable("id") String code, @RequestBody Game game){

        Game updatedGame = gameService.updateGame(code, game);

        return new ResponseEntity<>(updatedGame, HttpStatus.OK);
    }


}
