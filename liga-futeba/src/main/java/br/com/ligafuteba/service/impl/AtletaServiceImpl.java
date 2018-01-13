package br.com.ligafuteba.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ligafuteba.dtos.EstatisticaAtletaDTO;
import br.com.ligafuteba.models.Atleta;
import br.com.ligafuteba.repositorys.AtletaRepository;
import br.com.ligafuteba.service.AtletaService;

@Service("AtletaService")
public class AtletaServiceImpl implements AtletaService {

    @Autowired
    private AtletaRepository repository;

    @Override
    public Atleta salvarAtleta(final Atleta atleta) {
        return repository.save(atleta);
    }

    @Override
    public Iterable<Atleta> localizarTodosAtletas() {
        return repository.findAll();
    }

    @Override
    public Atleta localizarPorId(final Integer id) {
        return repository.findOne(id);
    }

    @Override
    public Atleta atualizarAtleta(final Atleta atleta) {
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
	public Iterable<EstatisticaAtletaDTO> getEstatisticasAtletas() {
		List<Object[]> result = (List<Object[]>) repository.getEstatisticasAtleta();
		List<EstatisticaAtletaDTO> estatisticasAtletasDTOs = new ArrayList<>();
		
		if (result != null && !result.isEmpty()){
			for (Object[] object : result) {
				EstatisticaAtletaDTO estatisticaAtletaDTO = new EstatisticaAtletaDTO();
				
				estatisticaAtletaDTO.setNome(object[0]);
				estatisticaAtletaDTO.setGols(object[1]);
				estatisticaAtletaDTO.setAssists(object[2]);
				
				estatisticasAtletasDTOs.add(estatisticaAtletaDTO);
			}
		}
		return estatisticasAtletasDTOs;
	}

}
