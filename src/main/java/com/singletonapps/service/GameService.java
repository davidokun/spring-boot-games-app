package com.singletonapps.service;

import com.singletonapps.model.Game;
import com.singletonapps.model.GamesResponse;

/**
 * Created by David Cuellar on 8/06/17.
 */
public interface GameService {

    Game createGame(Game game);

    GamesResponse getAllGames();
}
