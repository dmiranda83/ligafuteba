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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.futeba.models.Place;
import br.com.futeba.services.PlaceService;
import br.com.futeba.utils.HeaderUtil;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class PlaceControllerV1 {

    private static final Logger logger = LoggerFactory
            .getLogger(PlaceControllerV1.class);

    @Autowired
    private PlaceService service;

    @GetMapping("/places")
    public ResponseEntity<List<Place>> listAll() {
        logger.info("Listing all places");
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping("/places/{id}")
    public ResponseEntity<Place> listById(@PathVariable Long id) {
        logger.info("Find category id: {}", id);
        Optional<Place> place = service.findById(id);
        return place.map(
                response -> ResponseEntity.ok().header(null).body(response))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND));
    }

    @GetMapping("/places/list/{name}")
    public ResponseEntity<Place> listByName(@PathVariable("name") String name) {
        logger.info("Find place: {}", name);
        Optional<Place> place = service.findByName(name);
        return place.map(
                response -> ResponseEntity.ok().header(null).body(response))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND));
    }

    @PostMapping("/places")
    public ResponseEntity<Place> save(@RequestBody final Place place) {
        Optional<Place> foundPlace = service.findByName(place.getName());
        if (place.getName() == null || (foundPlace.isPresent()
                && place.getName().equals(foundPlace.get().getName()))) {
            return getHttpStatusBadRequest("placeExists",
                    "A new place cannot exist");
        }
        try {
            Place placeSaved = service.save(place);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(placeSaved.getId())
                    .toUri();

            return ResponseEntity.created(uri)
                    .headers(HeaderUtil.createEntityCreationAlert(
                            Place.class.getName(),
                            placeSaved.getId().toString()))
                    .body(placeSaved);

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .headers(HeaderUtil
                            .createErrorAlert("Check if parameters are correct!"))
                    .body(null);
        }
    }

    @PutMapping("/places/{id}")
    public ResponseEntity<Place> update(@PathVariable("id") final long id,
            @RequestBody final Place place) {

        Optional<Place> foundPlace = service.findByName(place.getName());

        if (foundPlace.isPresent()
                && place.getName().equals(foundPlace.get().getName())) {
            return getHttpStatusBadRequest("categoryExists",
                    "A new category cannot exist");
        }
        place.setId(id);
        service.update(Optional.of(place));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/places/{id}")
    public ResponseEntity<Place> delete(@PathVariable("id") final long id) {
        logger.info("Deleting place id {}", id);
        service.deleteById(id);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityDeletionAlert(
                        Place.class.getName(), String.valueOf(id)))
                .build();
    }

    @DeleteMapping("/places")
    public void delete() {
        logger.info("deleting all places");
        service.deleteAll();
    }

    private ResponseEntity<Place> getHttpStatusBadRequest(String errorKey,
            String defaultMessage) {
        return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert(Place.class.getName(),
                        errorKey, defaultMessage))
                .body(null);
    }

}
