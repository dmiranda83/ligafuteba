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
		this.sanRemo = new Team(TestUtil.TEAM_SAN_REMO, false,
				TestUtil.TEAM_RESPONSABLE, TestUtil.TEAM_RESPONSABLE_PHONE_NUMBER);
		this.originais = new Team(TestUtil.TEAM_ORIGINAIS, true,
				TestUtil.TEAM_RESPONSABLE, TestUtil.TEAM_RESPONSABLE_PHONE_NUMBER);
		this.veneza = new Team(TestUtil.TEAM_VENEZA, true,
				TestUtil.TEAM_RESPONSABLE, TestUtil.TEAM_RESPONSABLE_PHONE_NUMBER);
		this.raca = new Team(TestUtil.TEAM_RACA, true,
				TestUtil.TEAM_RESPONSABLE, TestUtil.TEAM_RESPONSABLE_PHONE_NUMBER);

		this.game1 = new Game(1L, this.sanRemo, this.raca);
		this.game2 = new Game(2L, this.sanRemo, this.originais);
		this.game3 = new Game(3L, this.sanRemo, this.veneza);

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
