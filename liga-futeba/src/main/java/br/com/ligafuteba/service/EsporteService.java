package br.com.ligafuteba.service;

import br.com.ligafuteba.models.Esporte;

public interface EsporteService {

    Esporte salvarEsporte(Esporte esporte);

    Iterable<Esporte> localizarTodosEsportes();

    Esporte localizarPorId(Integer id);

    Esporte atualizarEsporte(Esporte esporteAtual);

    void deletarPosicaoPorId(Integer id);

    void deletarTodasPosicoes();

}
