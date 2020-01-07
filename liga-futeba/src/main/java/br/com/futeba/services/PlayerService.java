package br.com.futeba.services;

import java.util.Optional;

import br.com.futeba.dtos.PlayerStatsDTO;
import br.com.futeba.models.Player;

public interface PlayerService {

	Player save(Player atleta);

	Iterable<Player> findAll();

	Optional<Player> findById(long id);

	Optional<Player> update(Optional<Player> currentPlayer);

	Optional<Player> findByName(String name);

	void deleteById(long id);

	void deleteAll();

	Iterable<PlayerStatsDTO> getPlayerStats(Integer year);

}
