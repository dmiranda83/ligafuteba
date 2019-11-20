package br.com.futeba.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.futeba.models.Category;
import br.com.futeba.repositorys.CategoryRepository;
import br.com.futeba.service.CategoryService;

@Service("EsporteService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Override
    public Category save(final Category esporte) {
        return repository.save(esporte);
    }

    @Override
    public Iterable<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public Category findById(final Integer id) {
        return repository.findOne(id);
    }

    @Override
    public Category update(final Category esporte) {
        return repository.saveAndFlush(esporte);
    }

    @Override
    public void delete(final Integer id) {
        repository.delete(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

}
