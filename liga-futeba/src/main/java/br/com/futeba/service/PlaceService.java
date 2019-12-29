package br.com.futeba.service;

import java.util.Optional;

import br.com.futeba.models.Place;

public interface PlaceService {

    Place save(Place estabelecimento);

    Iterable<Place> findAll();

    Optional<Place> findById(Integer id);

    void deleteById(Integer id);

    void deleteAll();

    Optional<Place> update(Optional<Place> currentPlace);
}
