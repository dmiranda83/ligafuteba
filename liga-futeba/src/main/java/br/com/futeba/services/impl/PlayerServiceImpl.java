package br.com.futeba.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.futeba.dtos.PlayerStatsDto;
import br.com.futeba.models.Player;
import br.com.futeba.models.Team;
import br.com.futeba.repositories.PlayerRepository;
import br.com.futeba.services.PlayerService;

@Service("PlayerService")
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository repository;

    @Override
    public Player save(final Player atleta) {
        return repository.save(atleta);
    }

    @Override
    public Optional<Player> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Player> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Player> findById(final Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Player> update(final Optional<Player> atleta) {
        return repository.saveAndFlush(atleta);
    }

    @Override
    public void deleteById(final Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public List<Player> listPlayerByTeam(Team team) {
        List<Player> listPlayerByTeam = new ArrayList<>();
        repository.findAll()
                .stream()
                .filter(player -> player.getTeams().contains(team))
                .forEach(listPlayerByTeam::add);
        return listPlayerByTeam;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterable<PlayerStatsDto> getPlayerStats(Integer year) {
        List<Object[]> playerStats = (List<Object[]>) repository
                .getEstatisticasAtleta(year);

        List<PlayerStatsDto> estatisticasAtletasDTOs = new ArrayList<>();
        if (playerStats != null && !playerStats.isEmpty()) {
            playerStats.forEach(
                    stats -> buildStats(estatisticasAtletasDTOs, stats));
        }
        return estatisticasAtletasDTOs;
    }

    private void buildStats(List<PlayerStatsDto> estatisticasAtletasDTOs,
            Object[] stats) {
        PlayerStatsDto playerStats = PlayerStatsDto.builder()
                .name(stats[0])
                .goals(stats[1])
                .assists(stats[2])
                .frequency(stats[3])
                .goalsAverage(stats[4])
                .build();
        estatisticasAtletasDTOs.add(playerStats);
    }
}
