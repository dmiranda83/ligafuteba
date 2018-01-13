package br.com.ligafuteba.service;

import br.com.ligafuteba.dtos.EstatisticaAtletaDTO;
import br.com.ligafuteba.dtos.EstatisticasDTO;
import br.com.ligafuteba.models.Equipe;

public interface EquipeService {

    Equipe salvarEquipe(Equipe equipe);

    Iterable<Equipe> localizarTodasEquipes();

    Equipe localizarPorId(Integer id);

    Equipe atualizarEquipe(Equipe equipe);

    void deletarPosicaoPorId(Integer id);

    void deletarTodasPosicoes();

	Iterable<EstatisticasDTO> getEstatisticas();

}
