package br.com.futeba.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.futeba.models.Player;
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
    public Optional<Position> update(final Optional<Position> posicao) {
        return repository.saveAndFlush(posicao);
    }

    @Override
    public Optional<Position> findById(final Integer id) {
        return repository.findById(id);
    }

    @Override
    public void delete(final Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
