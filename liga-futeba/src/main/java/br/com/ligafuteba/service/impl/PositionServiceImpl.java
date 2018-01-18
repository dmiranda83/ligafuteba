package br.com.ligafuteba.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ligafuteba.models.Position;
import br.com.ligafuteba.repositorys.PositionRepository;
import br.com.ligafuteba.service.PositionService;

@Service("PosicaoService")
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository repository;

    @Override
    public Position savePosition(final Position posicao) {
        return repository.save(posicao);
    }

    @Override
    public Iterable<Position> findAllPositions() {
        return repository.findAll();
    }

    @Override
    public Position updatePosition(final Position posicao) {
        return savePosition(posicao);
    }

    @Override
    public Position findById(final Integer id) {
        return repository.findOne(id);
    }

    @Override
    public void deletePositionById(final Integer id) {
        repository.delete(id);
    }

    @Override
    public void deleteAllPositions() {
        repository.deleteAll();
    }
}
