package com.singletonapps.service.impl;

import com.singletonapps.model.Game;
import com.singletonapps.model.GamesResponse;
import com.singletonapps.repository.GameRepository;
import com.singletonapps.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by David Cuellar on 8/06/17.
 */
@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;


    @Override
    public Game createGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public GamesResponse getAllGames() {

        List<Game> games = gameRepository.findAll();
        GamesResponse response = new GamesResponse();
        response.setResults(games.size());
        response.setGames(games);

        return response;

    }

    @Override
    public Game updateGame(String id, Game game) {

        game.setId(id);
        return gameRepository.save(game);

    }
}
