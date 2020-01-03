package br.com.futeba.service;

import java.util.Optional;

import br.com.futeba.dtos.StatsDTO;
import br.com.futeba.models.Team;

public interface TeamService {

    Team save(Team equipe);

    Iterable<Team> findAll();

    Optional<Team> findById(long id);

    Optional<Team> update(Optional<Team> currentTeam);

    Optional<Team> findByName(String name);
    
    void delete(long id);

    void delete();

	Iterable<StatsDTO> getTeamStats(Integer year);


}
