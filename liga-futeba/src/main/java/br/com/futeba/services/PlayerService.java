package br.com.futeba.services;

import java.util.List;
import java.util.Optional;

import br.com.futeba.dtos.PlayerStatsDto;
import br.com.futeba.models.Player;
import br.com.futeba.models.Team;

public interface PlayerService {

    Player save(Player atleta);

    List<Player> findAll();

    Optional<Player> findById(Long id);

    Player update(Player currentPlayer);

    Optional<Player> findByName(String name);

    void deleteById(Long id);

    void deleteAll();

    Iterable<PlayerStatsDto> getPlayerStats(Integer year);

    List<Player> listPlayerByTeam(Team team);

}
