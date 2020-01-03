package br.com.futeba.controller;

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

import br.com.futeba.dtos.StatsDTO;
import br.com.futeba.models.Position;
import br.com.futeba.models.Team;
import br.com.futeba.service.TeamService;

@RestController
@CrossOrigin (origins = "*") 
@RequestMapping("/api/v1/team")
public class TeamControllerV1 {

    private static final Logger logger = LoggerFactory.getLogger(TeamControllerV1.class);

    @Autowired
    private TeamService service;

    @PostMapping("/salvar")
    public String save(@RequestBody final Team team) {

        try {

            service.save(team);

        } catch (Exception e) {
            return "Error save team: " + e.toString();
        }
        return "Team save sucessfully! (id = " + team.getId() + " nome = " + team.getName() + ")";
    }
    
    @GetMapping("/list/{name}")
    public @ResponseBody Optional<Team> listByName(@PathVariable("name") String name){
    	logger.info("Find team: {}", name);
    	return service.findByName(name);
    }

    @GetMapping("/list")
    public @ResponseBody Iterable<Team> list() {
        return service.findAll();
    }
    
    
    @GetMapping("/stats/{year}")
    public @ResponseBody Iterable<StatsDTO> getStats(@PathVariable("year") final Integer year) {
    	logger.info("Loading team stats");
    	return service.getTeamStats(year);
    }

    @PutMapping("/update/{id}")
    public Optional<Team> update(@PathVariable("id") final long id, @RequestBody final Team team) {

        Optional<Team> currentTeam = service.findById(id);

        if (currentTeam.isPresent()) {
        	logger.info("Updating team id {}", id);
        	currentTeam.get().setName(team.getName());
        	currentTeam.get().setAway(team.getAway());
        	currentTeam.get().setResponsibleName(team.getResponsibleName());
        	currentTeam.get().setPhoneContact1(team.getPhoneContact1());
        	currentTeam.get().setPhoneContact2(team.getPhoneContact2());
        	currentTeam.get().setCategory(team.getCategory());
        	currentTeam.get().setPlace(team.getPlace());
        	currentTeam.get().setPlayers(team.getPlayers());
        	return service.update(currentTeam);
        }else {
        	logger.error("Team id {} not found.", id);
        	return currentTeam;
        }
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") final long id) {
        Optional<Team> currentTeam = service.findById(id);

        if (currentTeam.isPresent()) {
        	logger.info("Deleting team id {}", id);
        	service.delete(id);
        }else {
        	logger.error("Team id {} not found.", id);
        }
    }

    @DeleteMapping("/delete")
    public void delete() {
        logger.info("Deleting all teams");
        service.delete();
    }
}
