package com.singletonapps.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.singletonapps.model.Game;
import com.singletonapps.model.GamesResponse;
import com.singletonapps.repository.GameRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Created by David Cuellar on 11/06/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class GameServiceImplTest {

    @InjectMocks
    private GameServiceImpl gameService;

    @Mock
    private GameRepository gameRepository;

    @Value("classpath:games.txt")
    private Resource gamesJsonFile;

    private List<Game> gamesJson;

    @Before
    public void setUp() throws IOException {

        MockitoAnnotations.initMocks(this);

        ObjectMapper om = new ObjectMapper();
        gamesJson = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(gamesJsonFile.getInputStream()))){
            String line;
            while ((line = br.readLine()) != null){
                gamesJson.add(om.readValue(line, Game.class));
            }
        }

    }

    @Test
    public void shouldReturnGameCreated(){
        when(gameRepository.save(gamesJson.get(0))).thenReturn(gamesJson.get(0));
        Game newGame = gameService.createGame(gamesJson.get(0));

        assertNotNull(newGame);
        assertEquals("tloz", newGame.getCode());
        assertEquals("10", newGame.getRating());

    }

    @Test
    public void shouldReturnGamesList(){
        when(gameRepository.findAll()).thenReturn(gamesJson);
        GamesResponse gamesResponse = gameService.getAllGames();

        assertNotNull(gamesResponse);
        assertEquals(3, gamesResponse.getGames().size());
        assertEquals(3, gamesResponse.getResults().intValue());
        assertEquals("Mega Man", gamesResponse.getGames().get(1).getName());
        assertEquals("sf", gamesResponse.getGames().get(2).getCode());
        assertEquals("mm", gamesResponse.getGames().get(1).getCode());
    }

}