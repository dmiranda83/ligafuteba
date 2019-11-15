package br.com.gamedate.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gamedate.dtos.StatisticsDTO;
import br.com.gamedate.models.Team;
import br.com.gamedate.repositorys.TeamRepository;
import br.com.gamedate.service.TeamService;

@Service("EquipeService")
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository repository;

    @Override
    public Team salvarEquipe(final Team equipe) {
        return repository.save(equipe);
    }

    @Override
    public Iterable<Team> localizarTodasEquipes() {
        return repository.findAll();
    }

    @Override
    public Team localizarPorId(final Integer id) {
        return repository.findOne(id);
    }

    @Override
    public Team atualizarEquipe(final Team equipe) {
        return repository.saveAndFlush(equipe);
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
	public Iterable<StatisticsDTO> getEstatisticas(Integer year) {
		List<Object[]> result = (List<Object[]>) repository.getTeamStats(year);
		List<StatisticsDTO> estatisticasDTOs = new ArrayList<>();
		
		if (result != null && !result.isEmpty()){
			for (Object[] object : result) {
				StatisticsDTO estatisticasDTO = new StatisticsDTO();
				
				estatisticasDTO.setQuadro(object[0]);
				estatisticasDTO.setPontos(object[1]);
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
