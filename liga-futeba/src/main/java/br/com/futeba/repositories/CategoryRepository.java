package br.com.futeba.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.futeba.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	public Optional<Category> findByName(String name);
}
