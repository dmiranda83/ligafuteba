package br.com.futeba.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.futeba.dtos.TeamStatsDTO;
import br.com.futeba.models.Team;
import br.com.futeba.repositories.TeamRepository;
import br.com.futeba.services.TeamService;

@Service("TeamService")
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamRepository repository;

	@Override
	public Team save(final Team equipe) {
		return repository.save(equipe);
	}

	@Override
	public List<Team> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Team> findById(final Long id) {
		return repository.findById(id);
	}

	@Override
	public Optional<Team> findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public Optional<Team> update(final Optional<Team> team) {
		return repository.saveAndFlush(team);
	}

	@Override
	public void deleteById(final Long id) {
		repository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<TeamStatsDTO> getTeamStats(Integer year) {

		List<Object[]> teamStats = (List<Object[]>) repository
				.getTeamStats(year);

		List<TeamStatsDTO> statsDTOs = new ArrayList<>();
		if (teamStats != null && !teamStats.isEmpty()) {
			teamStats.forEach(stats -> buildStats(statsDTOs, stats));
		}
		return statsDTOs;
	}

	private void buildStats(List<TeamStatsDTO> teamStatsDTOs, Object[] stats) {
		TeamStatsDTO teamStatsDTO = new TeamStatsDTO();
		teamStatsDTO.setSquad(stats[0]);
		teamStatsDTO.setPoints(stats[1]);
		teamStatsDTO.setGolsMarcados(stats[2]);
		teamStatsDTO.setGolsSofridos(stats[3]);
		teamStatsDTO.setSaldoDeGol(stats[4]);
		teamStatsDTO.setJogos(stats[5]);
		teamStatsDTO.setAproveitamento(stats[6]);
		teamStatsDTOs.add(teamStatsDTO);
	}
}
