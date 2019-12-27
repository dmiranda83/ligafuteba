package br.com.futeba.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.futeba.dtos.PlayerStatsDTO;
import br.com.futeba.models.Player;
import br.com.futeba.repositorys.PlayerRepository;
import br.com.futeba.service.PlayerService;

@Service("AtletaService")
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository repository;

    @Override
    public Player save(final Player atleta) {
        return repository.save(atleta);
    }

    @Override
    public Iterable<Player> findAll() {
        return repository.findAll();
    }

    @Override
    public Player findById(final Integer id) {
        return repository.findOne(id);
    }

    @Override
    public Player update(final Player atleta) {
        return repository.saveAndFlush(atleta);
    }

    @Override
    public void deleteById(final Integer id) {
        repository.delete(id);
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
