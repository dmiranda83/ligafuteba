package br.com.ligafuteba.service;

import java.util.List;

import br.com.ligafuteba.dtos.EstatisticaAtletaDTO;
import br.com.ligafuteba.models.Atleta;

public interface AtletaService {

    Atleta salvarAtleta(Atleta atleta);

    Iterable<Atleta> localizarTodosAtletas();

    Atleta localizarPorId(Integer id);

    Atleta atualizarAtleta(Atleta atleta);

    void deletarPosicaoPorId(Integer id);

    void deletarTodasPosicoes();

	Iterable<EstatisticaAtletaDTO> getEstatisticasAtletas(Integer year);

}
