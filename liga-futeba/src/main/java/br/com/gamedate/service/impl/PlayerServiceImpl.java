package br.com.gamedate.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gamedate.dtos.PlayerStatisticsDTO;
import br.com.gamedate.models.Player;
import br.com.gamedate.repositorys.PlayerRepository;
import br.com.gamedate.service.PlayerService;

@Service("AtletaService")
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository repository;

    @Override
    public Player salvarAtleta(final Player atleta) {
        return repository.save(atleta);
    }

    @Override
    public Iterable<Player> localizarTodosAtletas() {
        return repository.findAll();
    }

    @Override
    public Player localizarPorId(final Integer id) {
        return repository.findOne(id);
    }

    @Override
    public Player atualizarAtleta(final Player atleta) {
        return repository.saveAndFlush(atleta);
    }

    @Override
    public void deletarPosicaoPorId(final Integer id) {
        repository.delete(id);
    }

    @Override
    public void deletarTodasPosicoes() {
        repository.deleteAll();
    }

	@Override
	@SuppressWarnings("unchecked")
	public Iterable<PlayerStatisticsDTO> getEstatisticasAtletas(Integer year) {
		List<Object[]> result = (List<Object[]>) repository.getEstatisticasAtleta(year);
		List<PlayerStatisticsDTO> estatisticasAtletasDTOs = new ArrayList<>();
		
		if (result != null && !result.isEmpty()){
			for (Object[] object : result) {
				PlayerStatisticsDTO estatisticaAtletaDTO = new PlayerStatisticsDTO();
				
				estatisticaAtletaDTO.setNome(object[0]);
				estatisticaAtletaDTO.setGols(object[1]);
				estatisticaAtletaDTO.setAssists(object[2]);
				estatisticaAtletaDTO.setFreq(object[3]);
				estatisticaAtletaDTO.setMediaGols(object[4]);
				
				estatisticasAtletasDTOs.add(estatisticaAtletaDTO);
			}
		}
		return estatisticasAtletasDTOs;
	}
}
