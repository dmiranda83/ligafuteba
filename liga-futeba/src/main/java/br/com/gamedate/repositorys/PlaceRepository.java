package br.com.gamedate.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gamedate.models.Place;

public interface PlaceRepository extends JpaRepository<Place, Integer> {

}
