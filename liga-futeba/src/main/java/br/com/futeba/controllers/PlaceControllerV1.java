package br.com.futeba.controllers;

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

import br.com.futeba.models.Place;
import br.com.futeba.services.PlaceService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/place")
public class PlaceControllerV1 {

	private static final Logger logger = LoggerFactory
			.getLogger(PlaceControllerV1.class);

	@Autowired
	private PlaceService service;

	@PostMapping("/save")
	public String save(@RequestBody final Place place) {
		try {
			service.save(place);

		} catch (Exception e) {
			return "Error saving place: " + e.toString();
		}
		return "Place successfully save! (id = " + place.getId() + " nome = "
				+ place.getName() + ")";
	}

	@GetMapping("/list/{name}")
	public @ResponseBody Optional<Place> listByName(
			@PathVariable("name") String name) {
		logger.info("Find place: {}", name);
		return service.findByName(name);
	}

	@GetMapping("/list")
	public @ResponseBody List<Place> listar() {
		logger.info("Listing all places");
		return service.findAll();
	}

	@PutMapping("/update/{id}")
	public Optional<Place> update(@PathVariable("id") final long id,
			@RequestBody final Place place) {

		logger.info("Updating id place {}", id);
		Optional<Place> currentPlace = service.findById(id);

		if (currentPlace.isPresent()) {
			currentPlace.get().setName(place.getName());
			currentPlace.get().setType(place.getType());
			currentPlace.get().setAddress(place.getAddress());
			currentPlace.get().setCity(place.getCity());
			currentPlace.get().setNeighborhood(place.getNeighborhood());
			currentPlace.get().setZipCode(place.getZipCode());
		} else {
			logger.error("id place {} not found.", id);
			return currentPlace;
		}

		return service.update(currentPlace);
	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") final long id) {
		Optional<Place> currentPlace = service.findById(id);

		if (currentPlace.isPresent()) {
			logger.info("Deleting place id {}", id);
			service.deleteById(id);
		} else {
			logger.error("Id place {} not found.", id);
		}

		return "Place delete successfully! (id = " + currentPlace.get().getId()
				+ " nome = " + currentPlace.get().getName() + ")";
	}

	@DeleteMapping("/deletar")
	public void delete() {

		logger.info("deleting all places");
		service.deleteAll();
	}
}
