package br.com.ligafuteba.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ligafuteba.dtos.EstatisticaAtletaDTO;
import br.com.ligafuteba.dtos.EstatisticasDTO;
import br.com.ligafuteba.models.Equipe;
import br.com.ligafuteba.repositorys.EquipeRepository;
import br.com.ligafuteba.service.EquipeService;

@Service("EquipeService")
public class EquipeServiceImpl implements EquipeService {

    @Autowired
    private EquipeRepository repository;

    @Override
    public Equipe salvarEquipe(final Equipe equipe) {
        return repository.save(equipe);
    }

    @Override
    public Iterable<Equipe> localizarTodasEquipes() {
        return repository.findAll();
    }

    @Override
    public Equipe localizarPorId(final Integer id) {
        return repository.findOne(id);
    }

    @Override
    public Equipe atualizarEquipe(final Equipe equipe) {
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
	public Iterable<EstatisticasDTO> getEstatisticas() {
		List<Object[]> result = (List<Object[]>) repository.getEstatistica();
		List<EstatisticasDTO> estatisticasDTOs = new ArrayList<>();
		
		if (result != null && !result.isEmpty()){
			for (Object[] object : result) {
				EstatisticasDTO estatisticasDTO = new EstatisticasDTO();
				
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
