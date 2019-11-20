package br.com.futeba.service;

import br.com.futeba.dtos.StatisticsDTO;
import br.com.futeba.models.Team;

public interface TeamService {

    Team salvarEquipe(Team equipe);

    Iterable<Team> localizarTodasEquipes();

    Team localizarPorId(Integer id);

    Team atualizarEquipe(Team equipe);

    void deletarPosicaoPorId(Integer id);

    void deletarTodasPosicoes();

	Iterable<StatisticsDTO> getEstatisticas(Integer year);

}
