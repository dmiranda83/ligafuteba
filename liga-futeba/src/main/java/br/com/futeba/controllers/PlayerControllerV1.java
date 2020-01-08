package br.com.futeba.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import br.com.futeba.dtos.PlayerStatsDTO;
import br.com.futeba.models.Game;
import br.com.futeba.models.Place;
import br.com.futeba.models.Player;
import br.com.futeba.services.PlayerService;
import br.com.futeba.utils.HeaderUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class PlayerControllerV1 {

	private static final Logger logger = LoggerFactory
			.getLogger(PlayerControllerV1.class);

	@Autowired
	private PlayerService service;

	@GetMapping(value = "/players", produces = "application/json")
	@ApiOperation(value = "Return a list of players")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Return a list of players"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 404, message = "feature do not found"),
			@ApiResponse(code = 403, message = "You do not have permission to access this feature"),
			@ApiResponse(code = 500, message = "An exception was thrown"),})
	public @ResponseBody ResponseEntity<List<Player>> listAll() {
		logger.info("Listing all players");
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping("/players/{id}")
	public ResponseEntity<Player> listById(@PathVariable Long id) {
		logger.info("Find player id: {}", id);
		Optional<Player> player = service.findById(id);
		return player.map(
				response -> ResponseEntity.ok().headers(null).body(response))
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND));
	}

	@GetMapping("/players/list/{name}")
	public @ResponseBody ResponseEntity<Player> listByName(
			@PathVariable("name") String name) {
		logger.info("Find player: {}", name);
		Optional<Player> player = service.findByName(name);
		return player.map(
				response -> ResponseEntity.ok().headers(null).body(response))
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND));

	}

	@GetMapping("/players/list/{year}")
	public @ResponseBody Iterable<PlayerStatsDTO> getStats(
			@PathVariable("year") final Integer year) {
		logger.info("Loading player stats");
		return service.getPlayerStats(year);
	}

	@PostMapping("/players")
	public ResponseEntity<Player> save(@RequestBody final Player player) {
		try {
			Player playerSaved = service.save(player);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(player.getId()).toUri();

			return ResponseEntity.created(uri)
					.headers(HeaderUtil.createEntityCreationAlert(
							Place.class.getName(),
							playerSaved.getId().toString()))
					.body(playerSaved);

		} catch (Exception e) {
			return ResponseEntity.badRequest().headers(HeaderUtil
					.createErrorAlert("Check if parameters are correct!"))
					.body(null);
		}
	}

	@PutMapping("/players/{id}")
	public ResponseEntity<Player> update(@PathVariable("id") final long id,
			@RequestBody final Player player) {

		player.setId(id);
		service.update(Optional.of(player));
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/players/{id}")
	public ResponseEntity<Player> delete(@PathVariable("id") final long id) {
		logger.info("Deleting player id {}", id);
		service.deleteById(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(
				Game.class.getName(), String.valueOf(id))).build();
	}

	@DeleteMapping("/players")
	public void deleteAll() {
		logger.info("Deleting all players");
		service.deleteAll();
	}
}
