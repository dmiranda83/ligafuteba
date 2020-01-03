package br.com.futeba.service;

import java.util.Optional;

import br.com.futeba.models.Category;

public interface CategoryService {

	Category save(Category esporte);

	Iterable<Category> findAll();

	Optional<Category> findById(Long id);

	Optional<Category> findByName(String name);

	Category update(Optional<Category> currentTeamCategory);

	void delete(Long id);

	void deleteAll();
}
