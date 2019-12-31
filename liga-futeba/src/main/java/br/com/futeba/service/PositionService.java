package br.com.futeba.service;

import java.util.Optional;

import br.com.futeba.models.Position;

public interface PositionService {

    Position save(Position position);

    Iterable<Position> findAll();

    Optional<Position> update(Optional<Position> currentPlayerPosition);

    Optional<Position> findById(Integer id);

    Optional<Position> findByName(String name);
    
    void delete(Integer id);

    void deleteAll();



}
