package br.com.futeba.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.futeba.config.H2JpaConfig;
import br.com.futeba.models.Game;
import br.com.futeba.models.Team;
import br.com.futeba.utils.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = H2JpaConfig.class)
public class GameRepositoryIntegationTest {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private TeamRepository teamRepository;

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
                .homeTeam(this.sanRemo)
                .awayTeam(this.raca)
                .build();
        this.game2 = Game.builder()
                .homeTeam(this.sanRemo)
                .awayTeam(this.originais)
                .build();
        this.game3 = Game.builder()
                .homeTeam(this.sanRemo)
                .awayTeam(this.veneza)
                .build();
    }

    @Test
    public void givenAllGamesInDBWhenFindAllThenReturnOptionalWithAllGames() {
        teamRepository.save(this.sanRemo);
        teamRepository.save(this.raca);
        teamRepository.save(this.originais);
        teamRepository.save(this.veneza);

        gameRepository.save(this.game1);
        gameRepository.save(this.game2);
        gameRepository.save(this.game3);

        List<Game> foundAllGames = gameRepository.findAll();

        assertThat(foundAllGames.size()).isEqualTo(3);
    }

    @Test
    public void givenGameInDBWhenFindByIdThenReturnOptionalWithGame() {
        teamRepository.save(this.sanRemo);
        teamRepository.save(this.raca);
        gameRepository.save(this.game1);

        Optional<Game> foundGame = gameRepository.findById(game1.getId());

        assertThat(foundGame.isPresent()).isEqualTo(true);
        assertThat(foundGame.get().getAwayTeam().getName())
                .isEqualTo(TestUtil.TEAM_RACA);
    }

    @Test
    public void givenGameInDBWhenDeleteThenReturnEmptyOptional() {
        teamRepository.save(this.sanRemo);
        teamRepository.save(this.raca);
        gameRepository.save(this.game1);

        Optional<Game> gameBeforeDelete = gameRepository
                .findById(game1.getId());
        assertThat(gameBeforeDelete.isPresent()).isEqualTo(true);

        gameRepository.delete(this.game1);
        Optional<Game> gameAfterDelete = gameRepository
                .findById(this.game1.getId());
        assertThat(gameAfterDelete.isPresent()).isEqualTo(false);
    }

    @Test
    public void givenGameInDBWhenDeleteByIdThenReturnEmptyOptional() {
        teamRepository.save(this.sanRemo);
        teamRepository.save(this.raca);
        gameRepository.save(this.game1);

        Optional<Game> gameBeforeDelete = gameRepository
                .findById(game1.getId());
        assertThat(gameBeforeDelete.isPresent()).isEqualTo(true);

        gameRepository.deleteById(game1.getId());
        Optional<Game> gameAfterDelete = gameRepository
                .findById(this.game1.getId());
        assertThat(gameAfterDelete.isPresent()).isEqualTo(false);
    }

    @Test
    public void givenGamesInDBWhenDeleteAllThenReturnEmptyOptional() {
        teamRepository.save(this.sanRemo);
        teamRepository.save(this.raca);
        teamRepository.save(this.originais);
        teamRepository.save(this.veneza);

        gameRepository.save(this.game1);
        gameRepository.save(this.game2);
        gameRepository.save(this.game3);

        List<Game> foundAllGamesBeforeDelete = gameRepository.findAll();
        assertThat(foundAllGamesBeforeDelete.isEmpty()).isEqualTo(false);

        gameRepository.deleteAll();

        List<Game> foundAllGamesAfterDelete = gameRepository.findAll();
        assertThat(foundAllGamesAfterDelete.isEmpty()).isEqualTo(true);
    }

    @Test
    public void givenGameInDBWhenUpdateThenReturnGameUpdated() {
        teamRepository.save(this.sanRemo);
        teamRepository.save(this.raca);
        teamRepository.save(this.originais);
        gameRepository.save(this.game1);

        Optional<Game> gameBeforeUpdate = gameRepository
                .findById(game1.getId());
        assertThat(gameBeforeUpdate.get().getHomeTeam().getName())
                .isEqualTo(TestUtil.TEAM_SAN_REMO);

        game1.setHomeTeam(this.originais);

        gameRepository.saveAndFlush(game1);
        Optional<Game> gameAfterUpdate = gameRepository.findById(game1.getId());
        assertThat(gameAfterUpdate.get().getHomeTeam().getName())
                .isEqualTo(TestUtil.TEAM_ORIGINAIS);
    }

    @After
    public void cleanUp() {
        gameRepository.deleteAll();
        teamRepository.deleteAll();
    }

}
