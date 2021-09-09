package br.com.futeba.services;

import java.util.List;
import java.util.Optional;

import br.com.futeba.models.Place;

public interface PlaceService {

    Place save(Place estabelecimento);

    List<Place> findAll();

    Optional<Place> findById(Long id);

    Optional<Place> findByName(String name);

    Optional<Place> findByZipCode(String zipCode);

    void deleteById(Long id);

    void deleteAll();

    Optional<Place> update(Optional<Place> currentPlace);

    List<Place> listPlacesWithoutZipCode();
}
