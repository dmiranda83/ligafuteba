package br.com.futeba.services;

import java.util.List;
import java.util.Optional;

import br.com.futeba.dtos.TeamStatsDTO;
import br.com.futeba.models.Team;

public interface TeamService {

	Team save(Team equipe);

	List<Team> findAll();

	Optional<Team> findById(Long id);

	Optional<Team> update(Optional<Team> currentTeam);

	Optional<Team> findByName(String name);

	void deleteById(Long id);

	void deleteAll();

	Iterable<TeamStatsDTO> getTeamStats(Integer year);

}
