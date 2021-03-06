package br.com.futeba.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.futeba.models.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {

	Optional<Place> saveAndFlush(Optional<Place> estabelecimento);

	Optional<Place> findByName(String name);

}
