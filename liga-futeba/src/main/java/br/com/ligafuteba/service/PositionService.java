package br.com.ligafuteba.service;

import br.com.ligafuteba.models.Position;

public interface PositionService {

    Position savePosition(Position position);

    Iterable<Position> findAllPositions();

    Position updatePosition(Position position);

    Position findById(Integer id);

    void deletePositionById(Integer id);

    void deleteAllPositions();


}
