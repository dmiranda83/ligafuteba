package br.com.futeba.services;

import java.util.List;
import java.util.Optional;

import br.com.futeba.models.Game;

public interface GameService {

	Game save(Game game);

    List<Game> findAll(Integer year);

    Optional<Game> findById(long id);

    Optional<Game> update(Optional<Game> currentGame);

    void deleteAll();

    void deleteById(long id);

}
