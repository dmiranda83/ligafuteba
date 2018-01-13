package br.com.ligafuteba.service;

import br.com.ligafuteba.models.Posicao;

public interface PosicaoService {

    Posicao salvarPosicao(Posicao posicao);

    Iterable<Posicao> localizarTodasPosicao();

    Posicao atualizarPosicao(Posicao posicao);

    Posicao localizarPorId(Integer id);

    void deletarPosicaoPorId(Integer id);

    void deletarTodasPosicoes();

}
