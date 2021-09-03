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
import br.com.futeba.models.Player;
import br.com.futeba.models.Position;
import br.com.futeba.models.Team;
import br.com.futeba.services.PlayerService;
import br.com.futeba.utils.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = GameDateApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class PlayersControllerV1IntegrationTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PlayerService service;

    private List<Player> allPlayers;
    private List<Team> teamsDavi;
    private List<Team> teamsDiego;
    private List<Team> teamsElias;
    private Position fixo;
    private Position alaEsquerdo;
    private Player davi;
    private Player diego;
    private Player elias;
    private Team sanRemo;
    private Team originais;
    private Team veneza;

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

        this.teamsDiego = Arrays.asList(this.sanRemo, this.originais);
        this.teamsDavi = Arrays.asList(this.originais);
        this.teamsElias = Arrays.asList(this.sanRemo, this.originais,
                this.veneza);

        this.diego = new Player(1L, TestUtil.PLAYER_DIEGO, this.alaEsquerdo,
                this.teamsDiego);

        this.davi = new Player(2L, TestUtil.PLAYER_DAVI, this.fixo,
                this.teamsDavi);
        this.elias = new Player(3L, TestUtil.PLAYER_ELIAS, this.fixo,
                this.teamsElias);

        this.allPlayers = Arrays.asList(this.diego, this.davi, this.elias);

        given(service.findAll()).willReturn(allPlayers);
        given(service.findById(diego.getId())).willReturn(Optional.of(diego));
        given(service.save(any(Player.class))).willReturn(this.diego);
        doNothing().when(service).deleteById(1L);
        doNothing().when(service).deleteAll();
    }

    @Test
    public void givenPlayers_whenListAllPlayers_thenStatus200()
            throws Exception {
        mvc.perform(
                get("/api/v1/players").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)));

        verify(service, times(1)).findAll();
    }

    @Test
    public void givenPlayers_whenListGameById_thenStatus200() throws Exception {
        mvc.perform(get("/api/v1/players/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(TestUtil.PLAYER_DIEGO)));

        verify(service, times(1)).findById(1L);
    }

    @Test
    public void givenPlayers_whenListGameByNonexistentId_thenStatus404()
            throws Exception {
        mvc.perform(get("/api/v1/players/5")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service, times(1)).findById(5L);
    }

    @Test
    public void givenPlayers_whenSaveGame_thenStatus201() throws Exception {
        mvc.perform(post("/api/v1/players")
                .content(mapper.writeValueAsString(this.diego))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(TestUtil.PLAYER_DIEGO)));

        verify(service, times(1)).save(any(Player.class));
        ;
    }

    @Test
    public void givenPlayers_whenDeleteGame_thenStatus200() throws Exception {
        mvc.perform(
                delete("/api/v1/players").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(1)).deleteAll();
    }

    @Test
    public void givenPlayers_whenDeleteGameById_thenStatus200()
            throws Exception {

        mvc.perform(delete("/api/v1/players/1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(service, times(1)).deleteById(1L);
    }

}
