package br.com.futeba.services;

import java.util.List;
import java.util.Optional;

import br.com.futeba.models.Position;

public interface PositionService {

	Position save(Position position);

	List<Position> findAll();

	Optional<Position> update(Optional<Position> currentPlayerPosition);

	Optional<Position> findById(Long id);

	Optional<Position> findByName(String name);

	void deleteById(Long id);

	void deleteAll();

}
