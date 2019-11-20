package br.com.gamedate.service;

import br.com.gamedate.dtos.PlayerStatisticsDTO;
import br.com.gamedate.models.Player;

public interface PlayerService {

    Player salvarAtleta(Player atleta);

    Iterable<Player> localizarTodosAtletas();

    Player localizarPorId(Integer id);

    Player atualizarAtleta(Player atleta);

    void deletarPosicaoPorId(Integer id);

    void deletarTodasPosicoes();

	Iterable<PlayerStatisticsDTO> getEstatisticasAtletas(Integer year);

}
