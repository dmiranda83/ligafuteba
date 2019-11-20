package br.com.futeba.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.futeba.models.Game;
import br.com.futeba.repositorys.GameRepository;
import br.com.futeba.service.GameService;

@Service("GameService")
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository repository;

    @Override
    public Game save(final Game game) {
        return repository.save(game);
    }

    @Override
    public List<Game> findAll(Integer year) {
        return repository.getGames(year);
    }

    @Override
    public Game findById(final Integer id) {
        return repository.findOne(id);
    }

    @Override
    public Game update(final Game game) {
        return repository.saveAndFlush(game);
    }

    @Override
    public void deleteById(final Integer id) {
        repository.delete(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

}
