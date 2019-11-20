package br.com.gamedate.repositorys;

import org.springframework.data.repository.CrudRepository;

import br.com.gamedate.models.Position;

public interface PositionRepository extends CrudRepository<Position, Integer> {

}
