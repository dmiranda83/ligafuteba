package br.com.futeba.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.futeba.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    public Category findByName(String name);

	public Optional<Category> saveAndFlush(Optional<Category> esporte);

}
