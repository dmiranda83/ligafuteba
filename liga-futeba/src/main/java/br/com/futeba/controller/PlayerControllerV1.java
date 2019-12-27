package br.com.futeba.controller;

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

import br.com.futeba.dtos.PlayerStatsDTO;
import br.com.futeba.models.Player;
import br.com.futeba.service.PlayerService;

@RestController
@CrossOrigin (origins = "*")
@RequestMapping("/api/v1/player")
public class PlayerControllerV1 {

    private static final Logger logger = LoggerFactory.getLogger(PlayerControllerV1.class);

    @Autowired
    private PlayerService service;

    @PostMapping("/save")
    public String save(@RequestBody final Player player) {

        try {
            service.save(player);

        } catch (Exception e) {
            return "Error saving player: " + e.toString();
        }
        return "Player successfully save! (id = " + player.getId() + " nome = " + player.getName() + ")";
    }

    @GetMapping("/list")
    public @ResponseBody Iterable<Player> list() {
        logger.info("Listing all players");
        return service.findAll();
    }
    
    @GetMapping("/stats/{year}")
    public @ResponseBody Iterable<PlayerStatsDTO> getStats(@PathVariable("year") final Integer year) {
    	logger.info("Loading player stats");
    	return service.getPlayerStats(year);
    }

    @PutMapping("/update/{id}")
    public Player update(@PathVariable("id") final Integer id, @RequestBody final Player player) {

        logger.info("Updating player id {}", id);
        Player currentPlayer = service.findById(id);

        if (currentPlayer == null) {
            logger.error("Player id {} not found.", id);
            return currentPlayer;
        }

        currentPlayer.setName(player.getName());
        currentPlayer.setPosition(player.getPosition());
        currentPlayer.setTeams(player.getTeams());

        return service.update(currentPlayer);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") final Integer id) {

        logger.info("Deleting player id {}", id);
        Player currentPlayer = service.findById(id);

        if (currentPlayer == null) {
            logger.error("Player id {} not found.", id);
        }
        service.deleteById(id);
    }

    @DeleteMapping("/delete")
    public void delete() {

        logger.info("Deleting all players");
        service.deleteAll();
    }
}
