package br.com.futeba.controllers;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.futeba.dtos.TeamDTO;
import br.com.futeba.dtos.TeamStatsDTO;
import br.com.futeba.models.Category;
import br.com.futeba.models.Place;
import br.com.futeba.models.Player;
import br.com.futeba.models.Position;
import br.com.futeba.models.Team;
import br.com.futeba.services.CategoryService;
import br.com.futeba.services.PlaceService;
import br.com.futeba.services.PlayerService;
import br.com.futeba.services.PositionService;
import br.com.futeba.services.TeamService;
import br.com.futeba.utils.HeaderUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class TeamControllerV1 {

    private static final Logger logger = LoggerFactory
            .getLogger(TeamControllerV1.class);

    @Autowired
    private TeamService service;
    @Autowired
    private PlaceService placeService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private PositionService positionService;

    @GetMapping("/teams")
    public @ResponseBody ResponseEntity<List<Team>> listAll() {
        logger.info("Listing all Teams");
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/teams/{id}")
    public ResponseEntity<Team> listById(@PathVariable final Long id) {
        logger.info("Find game id: {}", id);
        Optional<Team> team = service.findById(id);
        return team.map(
                response -> ResponseEntity.ok().header(null).body(response))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND));
    }

    @GetMapping("/teams/list/{name}")
    public ResponseEntity<Team> listByName(@PathVariable("name") final String name) {
        logger.info("Find Teams: {}", name);
        Optional<Team> team = service.findByName(name);
        return team.map(
                response -> ResponseEntity.ok().header(null).body(response))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/teams/stats/{year}", produces = "application/json")
    @ApiOperation(value = "Return a list of teams by year")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Return a list of teams by year"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "feature do not found"),
            @ApiResponse(code = 403, message = "You do not have permission to access this feature"),
            @ApiResponse(code = 500, message = "An exception was thrown"),
    })
    public @ResponseBody Iterable<TeamStatsDTO> getStats(
            @PathVariable("year") final Integer year) {
        logger.info("Loading team stats");
        return service.getTeamStats(year);
    }

    @PostMapping("/teams")
    public ResponseEntity<Team> save(@RequestBody final TeamDTO dto) {
        Team team = new Team();
        team.setId(dto.getId());
        team.setName(dto.getName());
        team.setAway(dto.getAway());
        team.setResponsibleName(dto.getResponsibleName());
        team.setPhoneContact1(dto.getPhoneContact1());
        Optional<Place> place = placeService.findById(Long.valueOf(dto.getPlaceID()));
        if (place.isPresent()) {
            team.setPlace(place.get());

        }

        Optional<Category> category = categoryService.findById(Long.valueOf(dto.getCategoryId()));
        if (category.isPresent()) {
            team.setCategory(category.get());
        }

        Optional<Team> foundTeam = service.findByName(team.getName());
        if (team.getName() == null || (foundTeam.isPresent()
                && team.getName().equals(foundTeam.get().getName()))) {
            return getHttpStatusBadRequest("teamExists",
                    "A new team cannot exist");
        }
        try {
            Team teamSaved = service.save(team);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(team.getId())
                    .toUri();

            return ResponseEntity.created(uri)
                    .headers(HeaderUtil.createEntityCreationAlert(
                            Team.class.getName(), teamSaved.getId().toString()))
                    .body(teamSaved);

        } catch (final Exception e) {
            return ResponseEntity.badRequest()
                    .headers(HeaderUtil
                            .createErrorAlert("Check if parameters are correct!"))
                    .body(null);
        }
    }

    @PutMapping("/teams/{id}")
    public ResponseEntity<Team> update(@PathVariable("id") final long id,
            @RequestBody final TeamDTO dto) {
        Optional<Team> foundTeam = service.findById(dto.getId());
        String placeID = StringUtils.isNotEmpty(dto.getPlaceID()) ? dto.getPlaceID() : "0";
        String categoryId = StringUtils.isNotEmpty(dto.getCategoryId()) ? dto.getCategoryId() : "0";
        Optional<Place> foundPlace = placeService.findById(Long.valueOf(placeID));
        Optional<Category> foundCategory = categoryService.findById(Long.valueOf(categoryId));
        Set<Player> players = new HashSet<>();
        Team team = new Team();
        if (foundTeam.isPresent()) {
            team = foundTeam.get();
            players = team.getPlayers();
        }

        if (dto.getPlayerId() != null) {
            Optional<Player> player = playerService.findById(Long.valueOf(dto.getPlayerId()));
            if (player.isPresent()) {
                players.add(player.get());
            }
        } else {
            Optional<Position> position = positionService.findById(Long.valueOf(dto.getPlayer().getPositionId()));
            Player playerBuild = Player.builder()
                    .name(dto.getPlayer().getName())
                    .position(position.isPresent() ? position.get() : null)
                    .build();
            players.add(playerBuild);
        }

        team.setId(dto.getId());
        team.setName(dto.getName() != null ? dto.getName() : team.getName());
        team.setAway(dto.getAway() != null ? dto.getAway() : team.getAway());
        team.setResponsibleName(dto.getResponsibleName() != null ? dto.getResponsibleName() : team.getResponsibleName());
        team.setPhoneContact1(dto.getPhoneContact1() != null ? dto.getPhoneContact1() : team.getPhoneContact1());
        team.setPhoneContact2(dto.getPhoneContact2() != null ? dto.getPhoneContact2() : team.getPhoneContact2());
        team.setPlace(foundPlace.isPresent() ? foundPlace.get() : team.getPlace());
        team.setCategory(foundCategory.isPresent() ? foundCategory.get() : team.getCategory());
        team.setPlayers(players);

        service.update(team);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/teams/{id}")
    public ResponseEntity<Team> delete(@PathVariable("id") final long id) {
        logger.info("Deleting team id {}", id);
        service.deleteById(id);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityDeletionAlert(
                        Team.class.getName(), String.valueOf(id)))
                .build();
    }

    @DeleteMapping("/teams")
    public void delete() {
        logger.info("Deleting all teams");
        service.deleteAll();
    }

    private ResponseEntity<Team> getHttpStatusBadRequest(final String errorKey,
            final String defaultMessage) {
        return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert(Team.class.getName(),
                        errorKey, defaultMessage))
                .body(null);
    }
}
