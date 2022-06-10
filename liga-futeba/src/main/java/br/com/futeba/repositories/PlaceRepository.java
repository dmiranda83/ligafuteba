package br.com.futeba.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.futeba.models.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    Optional<Place> saveAndFlush(Optional<Place> estabelecimento);

    Optional<Place> findByName(String name);

    @Query(value = "SELECT * FROM place WHERE zipCode = :zipCode", nativeQuery = true)
    Optional<Place> findByZipCode(@Param("zipCode") String zipCode);

}
