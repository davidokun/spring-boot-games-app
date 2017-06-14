package com.singletonapps.model;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by David Cuellar on 13/06/17.
 */
@Component
public class GamesResponse {

    private Integer results;
    private List<Game> games;

    public Integer getResults() {
        return results;
    }

    public void setResults(Integer results) {
        this.results = results;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
