package br.com.futeba.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.futeba.models.Place;
import br.com.futeba.repositorys.PlaceRepository;
import br.com.futeba.service.PlaceService;

@Service("EstabelecimentoService")
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceRepository repository;

    @Override
    public Place save(final Place estabelecimento) {
        return repository.save(estabelecimento);
    }

    @Override
    public Iterable<Place> findAll() {
        return repository.findAll();
    }

    @Override
    public Place update(final Place estabelecimento) {
        return repository.save(estabelecimento);
    }

    @Override
    public Place findById(final Integer id) {
        return repository.findOne(id);
    }

    @Override
    public void deleteById(final Integer id) {
        repository.delete(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
