package br.com.futeba.service;

import java.util.List;
import java.util.Optional;

import br.com.futeba.models.Game;

public interface GameService {

	Game save(Game game);

    List<Game> findAll(Integer year);

    Optional<Game> findById(Integer id);

    Optional<Game> update(Optional<Game> currentGame);

    void deleteAll();

    void deleteById(Integer id);

}
