package br.com.futeba.service;

import java.util.List;

import br.com.futeba.models.Game;

public interface GameService {

	Game save(Game jogo);

    List<Game> findAll(Integer year);

    Game findById(Integer id);

    Game update(Game jogo);

    void deleteAll();

    void deleteById(Integer id);

}
