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

import br.com.futeba.dtos.StatisticsDTO;
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

    @GetMapping("/list")
    public @ResponseBody Iterable<Team> list() {
        return service.findAll();
    }
    
    
    @GetMapping("/stats/{year}")
    public @ResponseBody Iterable<StatisticsDTO> getStats(@PathVariable("year") final Integer year) {
    	logger.info("Loading team stats");
    	return service.getTeamStats(year);
    }

    @PutMapping("/update/{id}")
    public Team update(@PathVariable("id") final Integer id, @RequestBody final Team team) {

        logger.info("Updating team id {}", id);
        Team currentTeam = service.findById(id);

        if (currentTeam == null) {
            logger.error("Team id {} not found.", id);
            return currentTeam;
        }

        currentTeam.setName(team.getName());
        currentTeam.setAway(team.getAway());
        currentTeam.setResponsibleName(team.getResponsibleName());
        currentTeam.setPhoneContact1(team.getPhoneContact1());
        currentTeam.setPhoneContact2(team.getPhoneContact2());
        currentTeam.setCategory(team.getCategory());
        currentTeam.setPlace(team.getPlace());
        currentTeam.setPlayers(team.getPlayers());

        return service.update(currentTeam);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") final Integer id) {

        logger.info("Deleting team id {}", id);
        Team currentTeam = service.findById(id);

        if (currentTeam == null) {
            logger.error("Team id {} not found.", id);
        }
        service.delete(id);
    }

    @DeleteMapping("/delete")
    public void delete() {
        logger.info("Deleting all teams");
        service.delete();
    }
}
