package br.com.futeba.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.futeba.models.Position;

public interface PositionRepository extends CrudRepository<Position, Long> {

	Optional<Position> saveAndFlush(Optional<Position> posicao);

	Optional<Position> findByName(String name);

}
