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

import br.com.futeba.models.Position;
import br.com.futeba.service.PositionService;

@RestController
@CrossOrigin (origins = "*")
@RequestMapping("/api/v1/playerPosition")
public class PlayerPositionControllerV1 {

    private static final Logger logger = LoggerFactory.getLogger(PlayerPositionControllerV1.class);

    @Autowired
    private PositionService service;

    @PostMapping("/save")
    public String save(@RequestBody final Position position) {
        try {
            service.save(position);
        } catch (Exception e) {
            return "Error saving player position: " + e.toString();
        }
        return "Player position save sucessfully! (id = " + position.getId() + " nome = " + position.getName() + ")";
    }

    @GetMapping("/list")
    public @ResponseBody Iterable<Position> list() {
        return service.findAll();
    }

    @PutMapping("/update/{id}")
    public Position update(@PathVariable("id") final Integer id, @RequestBody final Position position) {

        logger.info("Updating player position id {}", id);
        Position currentPlayerPosition = service.findById(id);

        if (currentPlayerPosition == null) {
            logger.error("Player position id {} not found.", id);
            return currentPlayerPosition;
        }
        return service.update(position);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") final Integer id) {

        logger.info("Deletando player position de id {}", id);
        Position currentPlayerPosition = service.findById(id);

        if (currentPlayerPosition == null) {
            logger.error("Player position id {} not found.", id);
        }
        service.delete(id);
    }

    @DeleteMapping("/delete")
    public void delete() {

        logger.info("Deleting all players position");
        service.deleteAll();
    }
}
