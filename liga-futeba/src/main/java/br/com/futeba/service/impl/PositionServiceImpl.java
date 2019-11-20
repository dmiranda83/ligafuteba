package br.com.futeba.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.futeba.models.Position;
import br.com.futeba.repositorys.PositionRepository;
import br.com.futeba.service.PositionService;

@Service("PosicaoService")
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository repository;

    @Override
    public Position save(final Position posicao) {
        return repository.save(posicao);
    }

    @Override
    public Iterable<Position> findAll() {
        return repository.findAll();
    }

    @Override
    public Position update(final Position posicao) {
        return save(posicao);
    }

    @Override
    public Position findById(final Integer id) {
        return repository.findOne(id);
    }

    @Override
    public void delete(final Integer id) {
        repository.delete(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
