package br.com.futeba.service;

import br.com.futeba.models.Category;

public interface CategoryService {

    Category save(Category esporte);

    Iterable<Category> findAll();

    Category findById(Integer id);

    Category update(Category esporteAtual);

    void delete(Integer id);

    void deleteAll();

}
