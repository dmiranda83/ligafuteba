package br.com.futeba.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.futeba.models.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

	public Optional<Game> findById(@Param("idParam") long id);

	public Optional<Game> saveAndFlush(Optional<Game> game);

	@Query(value = "SELECT * FROM jogos WHERE YEAR(data) = :year ORDER BY data ASC", nativeQuery = true)
	public List<Game> getGames(@Param("year") Integer year);

}
