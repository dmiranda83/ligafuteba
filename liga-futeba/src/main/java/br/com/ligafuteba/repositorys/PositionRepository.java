package br.com.ligafuteba.repositorys;

import org.springframework.data.repository.CrudRepository;

import br.com.ligafuteba.models.Position;

public interface PositionRepository extends CrudRepository<Position, Integer> {

}
