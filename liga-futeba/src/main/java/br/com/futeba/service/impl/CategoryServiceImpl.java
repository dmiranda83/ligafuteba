package br.com.futeba.service.impl;

import java.util.Optional;

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
    public Optional<Category> findById(final Integer id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Category> update(final Optional<Category> esporte) {
        return repository.saveAndFlush(esporte);
    }

    @Override
    public void delete(final Integer id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
