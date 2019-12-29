package br.com.futeba.service;

import java.util.Optional;

import br.com.futeba.dtos.PlayerStatsDTO;
import br.com.futeba.models.Player;

public interface PlayerService {

    Player save(Player atleta);

    Iterable<Player> findAll();

    Optional<Player> findById(Integer id);

    Optional<Player> update(Optional<Player> currentPlayer);

    void deleteById(Integer id);

    void deleteAll();

	Iterable<PlayerStatsDTO> getPlayerStats(Integer year);

}
