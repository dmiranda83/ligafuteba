package br.com.ligafuteba.service;

import br.com.ligafuteba.models.Estabelecimento;

public interface EstabelecimentoService {

    Estabelecimento salvarEstabelecimento(Estabelecimento estabelecimento);

    Iterable<Estabelecimento> localizarTodosEstabelecimento();

    Estabelecimento localizarPorId(Integer id);

    void deletarEstabelecimentoPorId(Integer id);

    void deletarTodosEstabelecimentos();

    Estabelecimento atualizarEstabelecimento(Estabelecimento estabelecimento);
}
