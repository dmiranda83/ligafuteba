package br.com.ligafuteba.service;

import br.com.ligafuteba.models.Jogo;

public interface JogoService {

	Jogo salvarJogo(Jogo jogo);

    Iterable<Jogo> localizarTodosJogos();

    Jogo localizarPorId(Integer id);

    Jogo atualizarJogo(Jogo jogo);

    void deletarTodasPosicoes();

    void deletarPosicaoPorId(Integer id);

}
