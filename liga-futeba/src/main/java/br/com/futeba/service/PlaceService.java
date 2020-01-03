package br.com.futeba.service;

import java.util.Optional;

import br.com.futeba.models.Place;

public interface PlaceService {

    Place save(Place estabelecimento);

    Iterable<Place> findAll();

    Optional<Place> findById(long id);

    Optional<Place> findByName(String name);
    
    void deleteById(long id);

    void deleteAll();

    Optional<Place> update(Optional<Place> currentPlace);

}
