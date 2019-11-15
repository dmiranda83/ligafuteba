package br.com.gamedate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gamedate.models.Category;
import br.com.gamedate.repositorys.CategoryRepository;
import br.com.gamedate.service.CategoryService;

@Service("EsporteService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Override
    public Category salvarEsporte(final Category esporte) {
        return repository.save(esporte);
    }

    @Override
    public Iterable<Category> localizarTodosEsportes() {
        return repository.findAll();
    }

    @Override
    public Category localizarPorId(final Integer id) {
        return repository.findOne(id);
    }

    @Override
    public Category atualizarEsporte(final Category esporte) {
        return repository.saveAndFlush(esporte);
    }

    @Override
    public void deletarPosicaoPorId(final Integer id) {
        repository.delete(id);
    }

    @Override
    public void deletarTodasPosicoes() {
        repository.deleteAll();
    }

}
