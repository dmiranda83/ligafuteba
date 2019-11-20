package br.com.futeba.service;

import br.com.futeba.models.Position;

public interface PositionService {

    Position save(Position position);

    Iterable<Position> findAll();

    Position update(Position position);

    Position findById(Integer id);

    void delete(Integer id);

    void deleteAll();


}
