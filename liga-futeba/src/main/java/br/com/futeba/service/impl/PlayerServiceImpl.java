package br.com.futeba.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.futeba.dtos.PlayerStatsDTO;
import br.com.futeba.models.Player;
import br.com.futeba.repositorys.PlayerRepository;
import br.com.futeba.service.PlayerService;

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
		List<Object[]> result = (List<Object[]>) repository.getEstatisticasAtleta(year);
		List<PlayerStatsDTO> estatisticasAtletasDTOs = new ArrayList<>();
		
		if (result != null && !result.isEmpty()){
			for (Object[] object : result) {
				PlayerStatsDTO estatisticaAtletaDTO = new PlayerStatsDTO();
				
				estatisticaAtletaDTO.setName(object[0]);
				estatisticaAtletaDTO.setGoals(object[1]);
				estatisticaAtletaDTO.setAssists(object[2]);
				estatisticaAtletaDTO.setFrequency(object[3]);
				estatisticaAtletaDTO.setGoalsAverage(object[4]);
				
				estatisticasAtletasDTOs.add(estatisticaAtletaDTO);
			}
		}
		return estatisticasAtletasDTOs;
	}
}
