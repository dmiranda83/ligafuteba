//package br.com.futeba.repositorys;
//
//import static org.junit.Assert.assertArrayEquals;
//import static org.junit.jupiter.api.Assertions.assertArrayEquals;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import br.com.futeba.models.Game;
//import br.com.futeba.models.Team;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//class GameRepositoryTest {
//	
//	@Autowired
//	private TestEntityManager entityManager;
//	
//	@Autowired
//	private GameRepository gameRepository;
//	
//	private List<Game> games = new ArrayList<>();
//
//	@Test
//	public void whenFindByName_thenReturnEmployee() {
//	    // given
//		Team originaisFutsal = createTeam(1, "Originais Futsal");
//		Team sanRemoFutsal = createTeam(2, "San Remo Futsal");
//		
//	    Game game1 = createGame(originaisFutsal, sanRemoFutsal);
//	    Game game2 = createGame(sanRemoFutsal, originaisFutsal);
//	    
//	    buildListGames(game1);
//	    buildListGames(game2);
//	    
//	    // when
//		List<Game> games = gameRepository.findByHomeTeamName(originaisFutsal.getName());
//	 
//	    // then
//		assertArrayEquals(getGames(), games);
//	}
//
//	private void buildListGames(Game game) {
//	    this.games.add(game);
//	}
//
//	private Game createGame(Team homeTeam, Team awayTeam) {
//		Game game = new Game();
//		entityManager.persist(game);
//	    entityManager.flush();
//	    game.setHomeTeam(homeTeam);
//	    game.setAwayTeam(awayTeam);
//		return game;
//	}
//
//	private Team createTeam(int id, String teamName) {
//		Team team = new Team();
//		team.setName(teamName);
//		team.setId(id);
//		return team;
//	}
//
//	public List<Game> getGames() {
//		return games;
//	}
//}
