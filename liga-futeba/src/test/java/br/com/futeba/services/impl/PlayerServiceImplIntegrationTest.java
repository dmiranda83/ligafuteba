package br.com.futeba.services.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.futeba.models.Player;
import br.com.futeba.models.Position;
import br.com.futeba.models.Team;
import br.com.futeba.repositories.PlayerRepository;
import br.com.futeba.services.PlayerService;
import br.com.futeba.utils.TestUtil;

@RunWith(SpringRunner.class)
public class PlayerServiceImplIntegrationTest {

    @TestConfiguration
    static class PlayerServiceImplIntegrationTestContextConfguration {

        @Bean
        public PlayerService playerService() {
            return new PlayerServiceImpl();
        }
    }

    @Autowired
    private PlayerService playerService;

    @MockBean
    private PlayerRepository playerRepository;

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

    private List<Player> allPlayers;

    @Before
    public void setUp() {
        this.sanRemo = Team.builder()
                .name(TestUtil.TEAM_SAN_REMO)
                .away(false)
                .responsibleName(TestUtil.TEAM_RESPONSABLE)
                .phoneContact1(TestUtil.TEAM_RESPONSABLE_PHONE_NUMBER)
                .build();
        this.originais = Team.builder()
                .name(TestUtil.TEAM_ORIGINAIS)
                .away(false)
                .responsibleName(TestUtil.TEAM_RESPONSABLE)
                .phoneContact1(TestUtil.TEAM_RESPONSABLE_PHONE_NUMBER)
                .build();
        this.veneza = Team.builder()
                .name(TestUtil.TEAM_VENEZA)
                .away(false)
                .responsibleName(TestUtil.TEAM_RESPONSABLE)
                .phoneContact1(TestUtil.TEAM_RESPONSABLE_PHONE_NUMBER)
                .build();

        this.alaEsquerdo = new Position(1L, TestUtil.POSITION_ALA_ESQUERDA);
        this.fixo = new Position(2L, TestUtil.POSITION_FIXO);

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

        Optional<Player> ofPlayer = Optional.of(this.diego);
        Mockito.when(playerRepository.save(this.diego))
                .thenReturn(ofPlayer.get());
        Mockito.when(playerRepository.findById(this.diego.getId()))
                .thenReturn(ofPlayer);
        Mockito.when(playerRepository.findByName(this.diego.getName()))
                .thenReturn(ofPlayer);
        Mockito.when(playerRepository.findAll()).thenReturn(allPlayers);

    }

    @Test
    public void whenValidPlayer_thenSavePlayer() {
        playerService.save(this.diego);
        assertThat(diego.getName()).isEqualTo(TestUtil.PLAYER_DIEGO);
    }

    @Test
    public void whenValidId_thenPlayerSholdBeFound() {
        Optional<Player> foundPlayer = playerService
                .findById(this.diego.getId());
        assertThat(foundPlayer.isPresent()).isEqualTo(true);
        assertThat(foundPlayer.get().getName())
                .isEqualTo(TestUtil.PLAYER_DIEGO);
    }

    @Test
    public void whenValidName_thenPlayerSholdBeFound() {
        Optional<Player> foundPlayer = playerService
                .findByName(this.diego.getName());
        assertThat(foundPlayer.isPresent()).isEqualTo(true);
        assertThat(foundPlayer.get().getName())
                .isEqualTo(TestUtil.PLAYER_DIEGO);
    }

    @Test
    public void whenListAll_thenReturnAllPlayers() {
        List<Player> foundAllPlayers = playerService.findAll();
        assertThat(foundAllPlayers.size()).isEqualTo(3);
    }

}
