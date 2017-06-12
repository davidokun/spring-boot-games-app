package com.singletonapps.service.impl;

import com.singletonapps.model.Game;
import com.singletonapps.repository.GameRepository;
import com.singletonapps.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
