package br.com.futeba.service;

import java.util.Optional;

import br.com.futeba.dtos.StatsDTO;
import br.com.futeba.models.Team;

public interface TeamService {

    Team save(Team equipe);

    Iterable<Team> findAll();

    Optional<Team> findById(Integer id);

    Optional<Team> update(Optional<Team> currentTeam);

    void delete(Integer id);

    void delete();

	Iterable<StatsDTO> getTeamStats(Integer year);

}
