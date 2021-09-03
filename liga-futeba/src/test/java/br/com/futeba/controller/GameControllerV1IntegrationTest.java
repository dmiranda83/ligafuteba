package br.com.futeba.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;

import br.com.futeba.GameDateApplication;
import br.com.futeba.models.Game;
import br.com.futeba.models.Team;
import br.com.futeba.services.GameService;
import br.com.futeba.utils.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = GameDateApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class GameControllerV1IntegrationTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GameService service;

    private List<Game> allGames;
    private Game game1;
    private Game game2;
    private Game game3;
    private Team sanRemo;
    private Team originais;
    private Team veneza;
    private Team raca;

    @Before
    public void setUp() {
        this.sanRemo = Team.builder()
                .id(1L)
                .name(TestUtil.TEAM_SAN_REMO)
                .away(false)
                .responsibleName(TestUtil.TEAM_RESPONSABLE)
                .phoneContact1(TestUtil.TEAM_RESPONSABLE_PHONE_NUMBER)
                .build();
        this.originais = Team.builder()
                .id(2L)
                .name(TestUtil.TEAM_ORIGINAIS)
                .away(false)
                .responsibleName(TestUtil.TEAM_RESPONSABLE)
                .phoneContact1(TestUtil.TEAM_RESPONSABLE_PHONE_NUMBER)
                .build();
        this.veneza = Team.builder()
                .id(3L)
                .name(TestUtil.TEAM_VENEZA)
                .away(false)
                .responsibleName(TestUtil.TEAM_RESPONSABLE)
                .phoneContact1(TestUtil.TEAM_RESPONSABLE_PHONE_NUMBER)
                .build();
        this.raca = Team.builder()
                .id(4L)
                .name(TestUtil.TEAM_RACA)
                .away(false)
                .responsibleName(TestUtil.TEAM_RESPONSABLE)
                .phoneContact1(TestUtil.TEAM_RESPONSABLE_PHONE_NUMBER)
                .build();

        this.game1 = new Game(1L, this.sanRemo, this.raca);
        this.game2 = new Game(1L, this.sanRemo, this.originais);
        this.game3 = new Game(1L, this.sanRemo, this.veneza);

        this.allGames = Arrays.asList(game1, game2, game3);
        given(service.findAll()).willReturn(allGames);
        given(service.findById(game1.getId())).willReturn(Optional.of(game1));
        given(service.save(any(Game.class))).willReturn(this.game1);
        doNothing().when(service).deleteById(1L);
        doNothing().when(service).deleteAll();
    }

    @Test
    public void givenGames_whenListAllGames_thenStatus200() throws Exception {
        mvc.perform(
                get("/api/v1/games").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)));

        verify(service, times(1)).findAll();
    }

    @Test
    public void givenGames_whenListGameById_thenStatus200() throws Exception {
        mvc.perform(
                get("/api/v1/games/1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath(
                        "$.homeTeam.name", is(TestUtil.TEAM_SAN_REMO)));

        verify(service, times(1)).findById(1L);
    }

    @Test
    public void givenGames_whenListGameByNonexistentId_thenStatus404()
            throws Exception {
        mvc.perform(
                get("/api/v1/games/5").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service, times(1)).findById(5L);
    }

    @Test
    public void givenGames_whenSaveGame_thenStatus201() throws Exception {
        mvc.perform(post("/api/v1/games")
                .content(mapper.writeValueAsString(this.game1))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath(
                        "$.homeTeam.name", is(TestUtil.TEAM_SAN_REMO)));

        verify(service, times(1)).save(any(Game.class));
        ;
    }

    @Test
    public void givenGames_whenDeleteGame_thenStatus200() throws Exception {
        mvc.perform(delete("/api/v1/games").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(1)).deleteAll();
    }

    @Test
    public void givenGames_whenDeleteGameById_thenStatus200() throws Exception {

        mvc.perform(delete("/api/v1/games/1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(service, times(1)).deleteById(1L);
    }

}
