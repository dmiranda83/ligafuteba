package br.com.gamedate.service;

import br.com.gamedate.dtos.StatisticsDTO;
import br.com.gamedate.models.Team;

public interface TeamService {

    Team salvarEquipe(Team equipe);

    Iterable<Team> localizarTodasEquipes();

    Team localizarPorId(Integer id);

    Team atualizarEquipe(Team equipe);

    void deletarPosicaoPorId(Integer id);

    void deletarTodasPosicoes();

	Iterable<StatisticsDTO> getEstatisticas(Integer year);

}
