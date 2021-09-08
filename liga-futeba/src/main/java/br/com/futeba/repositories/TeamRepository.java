package br.com.futeba.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.futeba.dtos.TeamStatsDTO;
import br.com.futeba.models.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query(value = "SELECT quadro, SUM(pontos) as pts, SUM(total_gols_mandante) as gm, SUM(total_gols_visitante) as gs, SUM(total_gols_mandante)  - SUM(total_gols_visitante) as sg, COUNT(*) as jgs,  (SUM(pontos) / (COUNT(*) * 3)) as ap FROM jogos WHERE YEAR(data) = :year GROUP BY quadro ORDER BY quadro ASC", nativeQuery = true)
    public List<TeamStatsDTO> getTeamStats(@Param("year") Integer year);

    public Optional<Team> saveAndFlush(Optional<Team> team);

    public Optional<Team> findByName(String name);
}
