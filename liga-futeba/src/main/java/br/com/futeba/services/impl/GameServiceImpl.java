package br.com.futeba.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.futeba.models.Game;
import br.com.futeba.repositories.GameRepository;
import br.com.futeba.services.GameService;

@Service("GameService")
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository repository;

    @Override
    public Game save(final Game game) {
        return repository.save(game);
    }

    @Override
    public List<Game> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Game> findByYear(Integer year) {
        return repository.getGames(year);
    }

    @Override
    public Optional<Game> findById(final Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Game> update(Optional<Game> game) {
        return repository.saveAndFlush(game);
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
    public List<Game> listGamesByYear(Integer year) {
        List<Game> listGamesByYear = new ArrayList<>();
        repository.findAll()
                .stream()
                .filter(game -> isGameOfTheYear(game, year))
                .forEach(listGamesByYear::add);
        return listGamesByYear;
    }

    private Boolean isGameOfTheYear(Game game, Integer year) {
        return year.equals(Integer.valueOf(game.getDateTime().getYear()));
    }
}
