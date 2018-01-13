package br.com.ligafuteba.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.ligafuteba.models.Equipe;

public interface EquipeRepository extends JpaRepository<Equipe, Integer> {
	
    @Query(value = "SELECT quadro, SUM(pontos) as pts, SUM(total_gols_mandante) as gm, SUM(total_gols_visitante) as gs, SUM(total_gols_mandante)  - SUM(total_gols_visitante) as sg, COUNT(*) as jgs,  (SUM(pontos) / (COUNT(*) * 3)) as ap FROM jogos GROUP BY quadro ORDER BY quadro ASC", nativeQuery = true)
    public List<?> getEstatistica();
}
