package br.com.futeba.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.futeba.models.Place;

public interface PlaceRepository extends JpaRepository<Place, Integer> {

}
