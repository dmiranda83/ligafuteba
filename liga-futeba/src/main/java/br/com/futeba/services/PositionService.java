package br.com.futeba.services;

import java.util.Optional;

import br.com.futeba.models.Position;

public interface PositionService {

    Position save(Position position);

    Iterable<Position> findAll();

    Optional<Position> update(Optional<Position> currentPlayerPosition);

    Optional<Position> findById(long id);

    Optional<Position> findByName(String name);
    
    void delete(long id);

    void deleteAll();



}
