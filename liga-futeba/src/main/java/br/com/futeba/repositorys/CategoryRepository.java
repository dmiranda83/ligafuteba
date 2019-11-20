package br.com.futeba.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.futeba.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
