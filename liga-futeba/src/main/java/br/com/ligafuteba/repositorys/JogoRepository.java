package br.com.ligafuteba.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.ligafuteba.models.Jogo;

public interface JogoRepository extends JpaRepository<Jogo, Integer> {

    public Jogo findById(@Param("idParam") Integer id);
    
    @Query(value = "SELECT * FROM jogos WHERE YEAR(data) = 2018 ORDER BY data ASC", nativeQuery = true)
    public Iterable<Jogo> getJogos();

}
