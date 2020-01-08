package br.com.futeba.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.futeba.models.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {

	Optional<Position> saveAndFlush(Optional<Position> posicao);

	Optional<Position> findByName(String name);

}
