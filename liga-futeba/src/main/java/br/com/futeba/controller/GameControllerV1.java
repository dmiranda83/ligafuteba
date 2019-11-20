package br.com.futeba.controller;


import java.util.List;

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

    @GetMapping("/list/{year}")
    public @ResponseBody List<Game> list(@PathVariable("year") final Integer year) {
        return service.findAll(year);
    }

    @PutMapping("/update/{id}")
    public Game updateGame(@PathVariable("id") final Integer id, @RequestBody final Game game) {

        logger.info("Updating id game {}", id);
        Game currentGame = service.findById(id);

        if (currentGame == null) {
            logger.error("Id game {} not found.", id);
            return currentGame;
        }
        
        logger.info("Home goals {}", game.getGamePlayerData());
        logger.info("Away goals {}", game.getAwayGoals());
        logger.info("Home assists {}", game.getHomeAssists());
        logger.info("Away assists {}", game.getAwayAssists());

        currentGame.setHomeTeam(game.getHomeTeam());
        currentGame.setAwayTeam(game.getAwayTeam());
        currentGame.setGamePlayerData(game.getGamePlayerData());
        currentGame.setAwayGoals(game.getAwayGoals());
        currentGame.setHomeAssists(game.getHomeAssists());
        currentGame.setAwayAssists(game.getAwayAssists());
        currentGame.setHomeTeamTotalGoals(game.getHomeTeamTotalGoals());
        currentGame.setAwayTeamTotalGoals(game.getAwayTeamTotalGoals());
        currentGame.setPoints(game.getPoints());

        return service.update(currentGame);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") final Integer id) {

        logger.info("Deleting id game {}", id);
        Game currentGame = service.findById(id);

        if (currentGame == null) {
            logger.error("Id game {} not found.", id);
        }
        service.deleteById(id);
    }

    @DeleteMapping("/delete")
    public void deleteAllGames() {

        logger.info("Deleting all games");
        service.deleteAll();
    }
}
