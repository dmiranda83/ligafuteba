package br.com.futeba.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.futeba.models.Category;
import br.com.futeba.repositorys.CategoryRepository;
import br.com.futeba.service.CategoryService;

@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Override
	public Category save(final Category category) {
		return repository.save(category);
	}

	@Override
	public Optional<Category> findByName(String name) {
		return repository.findByName(name);
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
	public Category update(final Optional<Category> category) {
		return repository.saveAndFlush(category.get());
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
