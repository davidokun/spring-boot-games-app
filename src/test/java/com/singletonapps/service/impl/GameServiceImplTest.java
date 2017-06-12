package com.singletonapps.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.singletonapps.model.Game;
import com.singletonapps.repository.GameRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

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

    private Game game;

    private String gameJson = "{\"name\" : \"The Legend of Zeld : Breath of the Wild\", \n" +
            "  \"code\" : \"tloz\",\n" +
            "  \"description\" : \"The Legend of Zelda: Breath of the Wild\",\n" +
            "  \"rating\" : \"10\",\n" +
            "  \"likes\" : 0,\n" +
            "  \"imageUrl\" : \"http://cdn3-www.comingsoon.net/assets/uploads/2016/06/zeldaheader2.jpg\",\n" +
            "  \"comments\" : [\n" +
            "    {\n" +
            "      \"author\" : \"davidokun\",\n" +
            "      \"comment\" : \"Yes, it's great!\",\n" +
            "      \"date\" : \"2017-06-08\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"author\" : \"johana\",\n" +
            "      \"comment\" : \"Cool game, 5 stars\",\n" +
            "      \"date\" : \"2017-06-05\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";


    @Before
    public void setUp() throws IOException {

        MockitoAnnotations.initMocks(this);

        ObjectMapper om = new ObjectMapper();
        game = om.readValue(gameJson, Game.class);

    }

    @Test
    public void shouldReturnGameCreated(){
        when(gameRepository.save(game)).thenReturn(game);
        Game newGame = gameService.createGame(game);

        assertNotNull(game);
        assertEquals(game.getCode(), newGame.getCode());
        assertEquals(game.getRating(), newGame.getRating());

    }

}