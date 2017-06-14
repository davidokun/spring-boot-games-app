package com.singletonapps.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.singletonapps.model.Game;
import com.singletonapps.model.GamesResponse;
import com.singletonapps.service.impl.GameServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class GamesControllerTest {

    @InjectMocks
    private GamesController gamesController;

    @Mock
    private GameServiceImpl gameService;

    @Mock
    private ResponseEntity responseEntity;

    @Value("classpath:games.txt")
    private Resource gamesJsonFile;

    private List<Game> gamesJson;

    private GamesResponse gamesResponse;

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

        gamesResponse = new GamesResponse();
        gamesResponse.setResults(3);
        gamesResponse.setGames(gamesJson);

    }

    @Test
    public void shouldReturnGameCreatedAndCode201(){
        when(gameService.createGame(gamesJson.get(0))).thenReturn(gamesJson.get(0));

        ResponseEntity newResponseEntity = gamesController.createGame(gamesJson.get(0));

        assertNotNull(newResponseEntity);
        assertEquals(HttpStatus.CREATED, newResponseEntity.getStatusCode());
        assertEquals("tloz", ((Game)newResponseEntity.getBody()).getCode());
        assertEquals("10", ((Game)newResponseEntity.getBody()).getRating());
    }

    @Test
    public void shouldReturnGamesListAndResultsWithCode200(){
        when(gameService.getAllGames()).thenReturn(gamesResponse);

        ResponseEntity newResponseEntity = gamesController.getAllGames();

        assertNotNull(newResponseEntity);
        assertEquals(HttpStatus.OK, newResponseEntity.getStatusCode());
        assertEquals("sf", ((GamesResponse)newResponseEntity.getBody()).getGames().get(2).getCode());
        assertEquals("tloz", ((GamesResponse)newResponseEntity.getBody()).getGames().get(0).getCode());
        assertEquals("mm", ((GamesResponse)newResponseEntity.getBody()).getGames().get(1).getCode());
        assertEquals(3, ((GamesResponse)newResponseEntity.getBody()).getResults().intValue());

    }

}