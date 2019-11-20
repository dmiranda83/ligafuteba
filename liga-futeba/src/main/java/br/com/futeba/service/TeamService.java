package br.com.futeba.service;

import br.com.futeba.dtos.StatisticsDTO;
import br.com.futeba.models.Team;

public interface TeamService {

    Team save(Team equipe);

    Iterable<Team> findAll();

    Team findById(Integer id);

    Team update(Team equipe);

    void delete(Integer id);

    void delete();

	Iterable<StatisticsDTO> getTeamStats(Integer year);

}
