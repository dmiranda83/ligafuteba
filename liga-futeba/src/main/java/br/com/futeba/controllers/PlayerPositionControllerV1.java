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

import br.com.futeba.models.Game;
import br.com.futeba.models.Place;
import br.com.futeba.models.Position;
import br.com.futeba.services.PositionService;
import br.com.futeba.utils.HeaderUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class PlayerPositionControllerV1 {

	private static final Logger logger = LoggerFactory
			.getLogger(PlayerPositionControllerV1.class);

	@Autowired
	private PositionService service;

	@GetMapping(value = "/playerPositions", produces = "application/json")
	@ApiOperation(value = "Return a list of player positions")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Return a list of player positions"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 404, message = "feature do not found"),
			@ApiResponse(code = 403, message = "You do not have permission to access this feature"),
			@ApiResponse(code = 500, message = "An exception was thrown"),})
	public @ResponseBody ResponseEntity<List<Position>> listAll() {
		logger.info("Listing all player positions");
		return ResponseEntity.ok().body(service.findAll());
	}

	@GetMapping("/playerPositions/{id}")
	public ResponseEntity<Position> listById(@PathVariable Long id) {
		logger.info("Find player postion id: {}", id);
		Optional<Position> postion = service.findById(id);
		return postion.map(
				response -> ResponseEntity.ok().header(null).body(response))
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND));
	}

	@GetMapping("playerPositions/list/{name}")
	public @ResponseBody ResponseEntity<Position> listByName(
			@PathVariable("name") String name) {
		logger.info("Find player position: {}", name);
		Optional<Position> postion = service.findByName(name);
		return postion.map(
				response -> ResponseEntity.ok().header(null).body(response))
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND));

	}

	@PostMapping("/playerPositions")
	public ResponseEntity<Position> save(@RequestBody final Position position) {
		Optional<Position> foundPosition = service
				.findByName(position.getName());
		if (position.getName() == null || (foundPosition.isPresent()
				&& position.getName().equals(foundPosition.get().getName()))) {
			return getHttpStatusBadRequest("categoryExists",
					"A new category cannot exist");
		}
		try {
			Position positionSaved = service.save(position);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(positionSaved.getId())
					.toUri();

			return ResponseEntity.created(uri)
					.headers(HeaderUtil.createEntityCreationAlert(
							Place.class.getName(),
							positionSaved.getId().toString()))
					.body(positionSaved);

		} catch (Exception e) {
			return ResponseEntity.badRequest().headers(HeaderUtil
					.createErrorAlert("Check if parameters are correct!"))
					.body(null);
		}
	}

	@PutMapping("/playerPositions/{id}")
	public ResponseEntity<Position> update(@PathVariable("id") final long id,
			@RequestBody final Position position) {

		position.setId(id);
		service.update(Optional.of(position));
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/playerPositions/{id}")
	public ResponseEntity<Position> delete(@PathVariable("id") final long id) {
		logger.info("Deleting position id {}", id);
		service.deleteById(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(
				Game.class.getName(), String.valueOf(id))).build();
	}

	@DeleteMapping("/playerPositions")
	public void delete() {
		logger.info("Deleting all players position");
		service.deleteAll();
	}

	private ResponseEntity<Position> getHttpStatusBadRequest(String errorKey,
			String defaultMessage) {
		return ResponseEntity.badRequest()
				.headers(HeaderUtil.createFailureAlert(Place.class.getName(),
						errorKey, defaultMessage))
				.body(null);
	}
}
