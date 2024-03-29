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

import br.com.futeba.models.Game;
import br.com.futeba.models.Team;
import br.com.futeba.repositories.GameRepository;
import br.com.futeba.services.GameService;
import br.com.futeba.utils.TestUtil;

@RunWith(SpringRunner.class)
public class GameServiceImplIntegrationTest {

    @TestConfiguration
    static class GameServiceImplIntegrationTestContextConfguration {

        @Bean
        public GameService gameService() {
            return new GameServiceImpl();
        }
    }

    @Autowired
    private GameService gameService;

    @MockBean
    private GameRepository gameRepository;

    private Game game1;
    private Game game2;
    private Game game3;
    private Team sanRemo;
    private Team originais;
    private Team veneza;
    private Team raca;

    private List<Game> allGames;

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
        this.raca = Team.builder()
                .name(TestUtil.TEAM_RACA)
                .away(false)
                .responsibleName(TestUtil.TEAM_RESPONSABLE)
                .phoneContact1(TestUtil.TEAM_RESPONSABLE_PHONE_NUMBER)
                .build();

        this.game1 = Game.builder()
                .id(1L)
                .homeTeam(this.sanRemo)
                .awayTeam(this.raca)
                .build();
        this.game2 = Game.builder()
                .id(2L)
                .homeTeam(this.sanRemo)
                .awayTeam(this.originais)
                .build();
        this.game3 = Game.builder()
                .id(3L)
                .homeTeam(this.sanRemo)
                .awayTeam(this.veneza)
                .build();

        this.allGames = Arrays.asList(this.game1, this.game2, this.game3);

        Optional<Game> ofGame = Optional.of(this.game1);
        Mockito.when(gameRepository.save(this.game1)).thenReturn(ofGame.get());
        Mockito.when(gameRepository.findById(this.game1.getId()))
                .thenReturn(ofGame);
        Mockito.when(gameRepository.findAll()).thenReturn(allGames);

    }

    @Test
    public void whenValidGame_thenSaveGame() {

        gameService.save(this.game1);

        assertThat(game1.getAwayTeam().getName()).isEqualTo(TestUtil.TEAM_RACA);
    }

    @Test
    public void whenValidId_thenGameSholdBeFound() {
        Optional<Game> foundGame = gameService.findById(this.game1.getId());
        assertThat(foundGame.isPresent()).isEqualTo(true);
        assertThat(foundGame.get().getHomeTeam().getName())
                .isEqualTo(TestUtil.TEAM_SAN_REMO);
    }

    @Test
    public void whenListAll_thenReturnAllGames() {
        List<Game> foundAllGames = gameService.findAll();
        assertThat(foundAllGames.size()).isEqualTo(3);
    }

}
