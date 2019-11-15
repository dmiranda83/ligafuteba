package br.com.gamedate.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gamedate.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
