package br.com.gamedate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gamedate.models.Place;
import br.com.gamedate.repositorys.PlaceRepository;
import br.com.gamedate.service.PlaceService;

@Service("EstabelecimentoService")
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PlaceRepository repository;

    @Override
    public Place salvarEstabelecimento(final Place estabelecimento) {
        return repository.save(estabelecimento);
    }

    @Override
    public Iterable<Place> localizarTodosEstabelecimento() {
        return repository.findAll();
    }

    @Override
    public Place atualizarEstabelecimento(final Place estabelecimento) {
        return repository.save(estabelecimento);
    }

    @Override
    public Place localizarPorId(final Integer id) {
        return repository.findOne(id);
    }

    @Override
    public void deletarEstabelecimentoPorId(final Integer id) {
        repository.delete(id);
    }

    @Override
    public void deletarTodosEstabelecimentos() {
        repository.deleteAll();
    }
}
