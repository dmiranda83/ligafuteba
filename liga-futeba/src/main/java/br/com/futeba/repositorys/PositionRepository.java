package br.com.futeba.repositorys;

import org.springframework.data.repository.CrudRepository;

import br.com.futeba.models.Position;

public interface PositionRepository extends CrudRepository<Position, Integer> {

}
