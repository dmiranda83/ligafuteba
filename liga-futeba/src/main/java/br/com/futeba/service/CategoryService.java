package br.com.gamedate.service;

import br.com.gamedate.models.Category;

public interface CategoryService {

    Category salvarEsporte(Category esporte);

    Iterable<Category> localizarTodosEsportes();

    Category localizarPorId(Integer id);

    Category atualizarEsporte(Category esporteAtual);

    void deletarPosicaoPorId(Integer id);

    void deletarTodasPosicoes();

}
