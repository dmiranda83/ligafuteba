package br.com.futeba.repositorys;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.futeba.models.Player;
import br.com.futeba.models.Position;

public interface PositionRepository extends CrudRepository<Position, Integer> {

	Optional<Position> saveAndFlush(Optional<Position> posicao);

	Optional<Position> findByName(String name);

}
