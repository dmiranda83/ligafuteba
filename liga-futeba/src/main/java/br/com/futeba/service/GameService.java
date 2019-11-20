package br.com.gamedate.service;

import java.util.List;

import br.com.gamedate.models.Game;

public interface GameService {

	Game salvarJogo(Game jogo);

    List<Game> localizarTodosJogos(Integer year);

    Game localizarPorId(Integer id);

    Game atualizarJogo(Game jogo);

    void deletarTodasPosicoes();

    void deletarPosicaoPorId(Integer id);

}
