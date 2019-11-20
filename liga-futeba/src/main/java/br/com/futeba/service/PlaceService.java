package br.com.futeba.service;

import br.com.futeba.models.Place;

public interface PlaceService {

    Place save(Place estabelecimento);

    Iterable<Place> findAll();

    Place findById(Integer id);

    void deleteById(Integer id);

    void deleteAll();

    Place update(Place estabelecimento);
}
