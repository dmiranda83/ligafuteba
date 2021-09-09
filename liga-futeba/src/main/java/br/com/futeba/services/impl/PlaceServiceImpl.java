package br.com.futeba.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.futeba.models.Place;
import br.com.futeba.repositories.PlaceRepository;
import br.com.futeba.services.PlaceService;

@Service("PlaceService")
@Transactional
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceRepository repository;

    @Override
    public Place save(final Place estabelecimento) {
        return repository.save(estabelecimento);
    }

    @Override
    public Optional<Place> findByName(final String name) {
        return repository.findByName(name);
    }

    @Override
    public Optional<Place> findByZipCode(final String zipCode) {
        return repository.findByZipCode(zipCode);
    }

    @Override
    public List<Place> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Place> update(final Optional<Place> estabelecimento) {
        return repository.saveAndFlush(estabelecimento);
    }

    @Override
    public Optional<Place> findById(final Long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(final Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public List<Place> listPlacesWithoutZipCode() {
        List<Place> listPlacesWithoutZipCode = new ArrayList<>();
        repository.findAll()
                .stream()
                .filter(Place::isPlaceWithoutZipCode)
                .forEach(listPlacesWithoutZipCode::add);
        return listPlacesWithoutZipCode;
    }
}
