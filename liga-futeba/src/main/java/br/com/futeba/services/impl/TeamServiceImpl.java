package br.com.futeba.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.futeba.dtos.TeamStatsDto;
import br.com.futeba.models.Player;
import br.com.futeba.models.Team;
import br.com.futeba.repositories.TeamRepository;
import br.com.futeba.services.TeamService;

@Service("TeamService")
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository repository;

    @Override
    public Team save(final Team equipe) {
        return repository.save(equipe);
    }

    @Override
    public List<Team> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Team> findById(final Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Team> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Team update(final Team team) {
        return repository.saveAndFlush(team);
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
    public List<Team> listTeamByPlayer(Player player) {
        List<Team> listTeamByPlayer = new ArrayList<>();
        repository.findAll()
                .stream()
                .filter(team -> team.getPlayers().contains(player))
                .forEach(listTeamByPlayer::add);
        return listTeamByPlayer;
    }

    @Override
    public List<TeamStatsDto> getTeamStats(Integer year) {

        List<TeamStatsDto> teamStats = repository
                .getTeamStats(year);

        List<TeamStatsDto> statsDTOs = new ArrayList<>();
        if (teamStats != null && !teamStats.isEmpty()) {
            teamStats.forEach(stats -> buildStats(statsDTOs, stats));
        }
        return statsDTOs;
    }

    private void buildStats(List<TeamStatsDto> teamStatsDTOs, TeamStatsDto stats) {
        TeamStatsDto teamStatsDTO = TeamStatsDto.builder()
                .squad(stats.getSquad())
                .points(stats.getPoints())
                .scoredGoals(stats.getScoredGoals())
                .concededGoals(stats.getConcededGoals())
                .balanceGoals(stats.getBalanceGoals())
                .games(stats.getGames())
                .winPercentage(stats.getWinPercentage())
                .build();
        teamStatsDTOs.add(teamStatsDTO);
    }
}
