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

import br.com.futeba.models.Place;
import br.com.futeba.service.PlaceService;

@RestController
@CrossOrigin (origins = "*")
@RequestMapping("/api/v1/place")
public class PlaceControllerV1 {

    private static final Logger logger = LoggerFactory.getLogger(PlaceControllerV1.class);

    @Autowired
    private PlaceService service;

    @PostMapping("/save")
    public String save(@RequestBody final Place place) {
        try {
            service.save(place);

        } catch (Exception e) {
            return "Error saving place: " + e.toString();
        }
        return "Place successfully save! (id = " + place.getId() + " nome = " + place.getName() + ")";
    }

    @GetMapping("/list")
    public @ResponseBody Iterable<Place> listar() {
        logger.info("Listing all places");
        return service.findAll();
    }

    @PutMapping("/update/{id}")
    public Place update(@PathVariable("id") final Integer id, @RequestBody final Place place) {

        logger.info("Updating id place {}", id);
        Place currentPlace = service.findById(id);

        if (currentPlace == null) {
            logger.error("id place {} not found.", id);
            return currentPlace;
        }

        currentPlace.setName(place.getName());
        currentPlace.setType(place.getType());
        currentPlace.setAddress(place.getAddress());
        currentPlace.setCity(place.getCity());
        currentPlace.setNeighborhood(place.getNeighborhood());
        currentPlace.setZipCode(place.getZipCode());

        return service.update(currentPlace);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") final Integer id) {

        logger.info("Deleting place id {}", id);
        Place currentPlace = service.findById(id);

        if (currentPlace == null) {
            logger.error("Id place {} not found.", id);
        }
        service.deleteById(id);
        return "Place delete successfully! (id = " + currentPlace.getId() + " nome = " + currentPlace.getName() + ")";
    }

    @DeleteMapping("/deletar")
    public void delete() {

        logger.info("deleting all places");
        service.deleteAll();
    }
}
