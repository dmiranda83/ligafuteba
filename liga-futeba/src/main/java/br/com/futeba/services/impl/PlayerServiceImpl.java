package br.com.futeba.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.futeba.dtos.PlayerStatsDTO;
import br.com.futeba.models.Player;
import br.com.futeba.repositories.PlayerRepository;
import br.com.futeba.services.PlayerService;

@Service("PlayerService")
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerRepository repository;

	@Override
	public Player save(final Player atleta) {
		return repository.save(atleta);
	}

	@Override
	public Optional<Player> findByName(String name) {
		return repository.findByName(name);
	}

	@Override
	public Iterable<Player> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Player> findById(final long id) {
		return repository.findById(id);
	}

	@Override
	public Optional<Player> update(final Optional<Player> atleta) {
		return repository.saveAndFlush(atleta);
	}

	@Override
	public void deleteById(final long id) {
		repository.deleteById(id);
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Iterable<PlayerStatsDTO> getPlayerStats(Integer year) {
		List<Object[]> playerStats = (List<Object[]>) repository
				.getEstatisticasAtleta(year);
		
		List<PlayerStatsDTO> estatisticasAtletasDTOs = new ArrayList<>();
		if (playerStats != null && !playerStats.isEmpty()) {
			playerStats.forEach(
					stats -> buildStats(estatisticasAtletasDTOs, stats));
		}
		return estatisticasAtletasDTOs;
	}

	private void buildStats(List<PlayerStatsDTO> estatisticasAtletasDTOs,
			Object[] stats) {
		PlayerStatsDTO estatisticaAtletaDTO = new PlayerStatsDTO();
		estatisticaAtletaDTO.setName(stats[0]);
		estatisticaAtletaDTO.setGoals(stats[1]);
		estatisticaAtletaDTO.setAssists(stats[2]);
		estatisticaAtletaDTO.setFrequency(stats[3]);
		estatisticaAtletaDTO.setGoalsAverage(stats[4]);
		estatisticasAtletasDTOs.add(estatisticaAtletaDTO);
	}
}
