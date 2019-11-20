package br.com.futeba.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.futeba.models.Game;

public interface GameRepository extends JpaRepository<Game, Integer> {

    public Game findById(@Param("idParam") Integer id);
    
    @Query(value = "SELECT * FROM jogos WHERE YEAR(data) = :year ORDER BY data ASC", nativeQuery = true)
    public List<Game> getGames(@Param("year") Integer year);
}
