package br.com.futeba.controller;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.futeba.models.Game;
import br.com.futeba.service.GameService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin (origins = "*")
@RequestMapping("/api/v1/game")
public class GameControllerV1 {

    private static final Logger logger = LoggerFactory.getLogger(GameControllerV1.class);

    @Autowired
    private GameService service;

    @PostMapping("/save")
    public String save(@RequestBody final Game game) {

        try {
            service.save(game);

        } catch (Exception e) {
            return "Error! Game not save: " + e.toString();
        }
        return "Game successfully save! (id = " + game.getId() + ")";
    }

    @GetMapping(value = "/list/{year}", produces = "application/json")
    @ApiOperation(value = "Return a list of games by year")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Return a list of games"),
        @ApiResponse(code = 403, message = "You do not have permission to access this feature"),
        @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    public @ResponseBody List<Game> list(@PathVariable("year") final Integer year) {
        return service.findAll(year);
    }

    @PutMapping("/update/{id}")
    public Optional<Game> updateGame(@PathVariable("id") final long id, @RequestBody final Game game) {

        logger.info("Updating id game {}", id);
        Optional<Game> currentGame = service.findById(id);

        
        logger.info("Home goals {}", game.getGamePlayerData());
        logger.info("Away goals {}", game.getAwayGoals());
        logger.info("Home assists {}", game.getHomeAssists());
        logger.info("Away assists {}", game.getAwayAssists());
        
		if(currentGame.isPresent()) {
			currentGame.get().setHomeTeam(game.getHomeTeam());
			currentGame.get().setAwayTeam(game.getAwayTeam());
			currentGame.get().setGamePlayerData(game.getGamePlayerData());
			currentGame.get().setAwayGoals(game.getAwayGoals());
			currentGame.get().setHomeAssists(game.getHomeAssists());
			currentGame.get().setAwayAssists(game.getAwayAssists());
			currentGame.get().setHomeTeamTotalGoals(game.getHomeTeamTotalGoals());
			currentGame.get().setAwayTeamTotalGoals(game.getAwayTeamTotalGoals());
			currentGame.get().setPoints(game.getPoints());
			
		}else {
				logger.error("Id game {} not found.", id);
				return currentGame;
		}
        return service.update(currentGame);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") final long id) {
        Optional<Game> currentGame = service.findById(id);

        if (currentGame.isPresent()) {
        	logger.info("Deleting id game {}", id);
        	service.deleteById(id);
        }else {
        	logger.error("Id game {} not found.", id);
        }
    }

    @DeleteMapping("/delete")
    public void deleteAllGames() {

        logger.info("Deleting all games");
        service.deleteAll();
    }
}
