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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
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

    private Game game;

    private String gameJson1 = "{\"code\":\"tloz\",\"name\":\"The Legend of Zeld : Breath of the Wild\",\"description\":\"The Legend of Zelda: Breath of the Wild es el título oficial del videojuego de acción-aventura de la serie The Legend of Zelda, desarrollado por Nintendo EPD (división de Nintendo creada por la unión de Nintendo EAD, Nintendo Software Planning & Development), en colaboración con Monolith Soft para Wii U y Nintendo Switch. Es la décimo octava entrega de la serie y la tercera en utilizar gráficos en alta definición (la primera fue The Legend of Zelda: The Wind Waker HD y la segunda es The Legend of Zelda: Twilight Princess HD). Se lanzó el 3 de marzo de 2017 para Wii U y Nintendo Switch.\",\"rating\":\"10\",\"likes\":\"0\",\"imageUrl\":\"http://cdn3-www.comingsoon.net/assets/uploads/2016/06/zeldaheader2.jpg\",\"reviews\":[{\"author\":\"davidokun\",\"comment\":\"Yes, it's great!\",\"date\":\"2017-06-08\"},{\"author\":\"johana\",\"comment\":\"Cool game, 5 stars\",\"date\":\"2017-06-05\"}],\"comments\":[{\"author\":\"davidokun\",\"comment\":\"Yes, it's great!\",\"date\":\"2017-06-08\"},{\"author\":\"johana\",\"comment\":\"Cool game, 5 stars\",\"date\":\"2017-06-05\"}]}";
    private String gameJson2 = "{\"code\":\"mm\",\"name\":\"Mega Man\",\"description\":\"The Legend of Zelda: Breath of the Wild es el título oficial del videojuego de acción-aventura de la serie The Legend of Zelda, desarrollado por Nintendo EPD (división de Nintendo creada por la unión de Nintendo EAD, Nintendo Software Planning & Development), en colaboración con Monolith Soft para Wii U y Nintendo Switch. Es la décimo octava entrega de la serie y la tercera en utilizar gráficos en alta definición (la primera fue The Legend of Zelda: The Wind Waker HD y la segunda es The Legend of Zelda: Twilight Princess HD). Se lanzó el 3 de marzo de 2017 para Wii U y Nintendo Switch.\",\"rating\":\"10\",\"likes\":\"0\",\"imageUrl\":\"http://cdn3-www.comingsoon.net/assets/uploads/2016/06/zeldaheader2.jpg\",\"reviews\":[{\"author\":\"davidokun\",\"comment\":\"Yes, it's great!\",\"date\":\"2017-06-08\"},{\"author\":\"johana\",\"comment\":\"Cool game, 5 stars\",\"date\":\"2017-06-05\"}],\"comments\":[{\"author\":\"davidokun\",\"comment\":\"Yes, it's great!\",\"date\":\"2017-06-08\"},{\"author\":\"johana\",\"comment\":\"Cool game, 5 stars\",\"date\":\"2017-06-05\"}]}";
    private String gameJson3 = "{\"code\":\"sf\",\"name\":\"Street Fighter\",\"description\":\"The Legend of Zelda: Breath of the Wild es el título oficial del videojuego de acción-aventura de la serie The Legend of Zelda, desarrollado por Nintendo EPD (división de Nintendo creada por la unión de Nintendo EAD, Nintendo Software Planning & Development), en colaboración con Monolith Soft para Wii U y Nintendo Switch. Es la décimo octava entrega de la serie y la tercera en utilizar gráficos en alta definición (la primera fue The Legend of Zelda: The Wind Waker HD y la segunda es The Legend of Zelda: Twilight Princess HD). Se lanzó el 3 de marzo de 2017 para Wii U y Nintendo Switch.\",\"rating\":\"10\",\"likes\":\"0\",\"imageUrl\":\"http://cdn3-www.comingsoon.net/assets/uploads/2016/06/zeldaheader2.jpg\",\"reviews\":[{\"author\":\"davidokun\",\"comment\":\"Yes, it's great!\",\"date\":\"2017-06-08\"},{\"author\":\"johana\",\"comment\":\"Cool game, 5 stars\",\"date\":\"2017-06-05\"}],\"comments\":[{\"author\":\"davidokun\",\"comment\":\"Yes, it's great!\",\"date\":\"2017-06-08\"},{\"author\":\"johana\",\"comment\":\"Cool game, 5 stars\",\"date\":\"2017-06-05\"}]}";

    private List<Game> gamesJson;

    @Before
    public void setUp() throws IOException {

        MockitoAnnotations.initMocks(this);

        ObjectMapper om = new ObjectMapper();
        game = om.readValue(gameJson1, Game.class);

        gamesJson = new ArrayList<>();
        gamesJson.add(om.readValue(gameJson1, Game.class));
        gamesJson.add(om.readValue(gameJson2, Game.class));
        gamesJson.add(om.readValue(gameJson3, Game.class));


    }

    @Test
    public void shouldReturnGameCreated(){
        when(gameRepository.save(game)).thenReturn(game);
        Game newGame = gameService.createGame(game);

        assertNotNull(game);
        assertEquals(game.getCode(), newGame.getCode());
        assertEquals(game.getRating(), newGame.getRating());

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
    }

}