package br.com.futeba.service;

import br.com.futeba.dtos.PlayerStatisticsDTO;
import br.com.futeba.models.Player;

public interface PlayerService {

    Player save(Player atleta);

    Iterable<Player> findAll();

    Player findById(Integer id);

    Player update(Player atleta);

    void deleteById(Integer id);

    void deleteAll();

	Iterable<PlayerStatisticsDTO> getPlayerStats(Integer year);

}
