package br.com.futeba.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.futeba.models.Place;
import br.com.futeba.repositorys.PlaceRepository;
import br.com.futeba.service.PlaceService;

@Service("PlaceService")
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceRepository repository;

    @Override
    public Place save(final Place estabelecimento) {
        return repository.save(estabelecimento);
    }
    
    @Override
	public Optional<Place> findByName(String name) {
		return repository.findByName(name);
	}
    @Override
    public Iterable<Place> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Place> update(final Optional<Place> estabelecimento) {
        return repository.saveAndFlush(estabelecimento);
    }

    @Override
    public Optional<Place> findById(final long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(final long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
