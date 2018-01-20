package br.com.ligafuteba.service;

import java.util.List;

import br.com.ligafuteba.models.Jogo;

public interface JogoService {

	Jogo salvarJogo(Jogo jogo);

    List<Jogo> localizarTodosJogos(Integer year);

    Jogo localizarPorId(Integer id);

    Jogo atualizarJogo(Jogo jogo);

    void deletarTodasPosicoes();

    void deletarPosicaoPorId(Integer id);

}
