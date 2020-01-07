package br.com.futeba.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.futeba.dtos.StatsDTO;
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
	public Iterable<Team> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Team> findById(final long id) {
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
	public void delete(final long id) {
		repository.deleteById(id);
	}

	@Override
	public void delete() {
		repository.deleteAll();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<StatsDTO> getTeamStats(Integer year) {
		List<Object[]> result = (List<Object[]>) repository.getTeamStats(year);
		List<StatsDTO> estatisticasDTOs = new ArrayList<>();

		if (result != null && !result.isEmpty()) {
			for (Object[] object : result) {
				StatsDTO estatisticasDTO = new StatsDTO();

				estatisticasDTO.setSquad(object[0]);
				estatisticasDTO.setPoints(object[1]);
				estatisticasDTO.setGolsMarcados(object[2]);
				estatisticasDTO.setGolsSofridos(object[3]);
				estatisticasDTO.setSaldoDeGol(object[4]);
				estatisticasDTO.setJogos(object[5]);
				estatisticasDTO.setAproveitamento(object[6]);

				estatisticasDTOs.add(estatisticasDTO);
			}
		}
		return estatisticasDTOs;
	}
}
