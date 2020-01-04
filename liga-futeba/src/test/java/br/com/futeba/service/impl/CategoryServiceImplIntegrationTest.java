package br.com.futeba.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.futeba.models.Category;
import br.com.futeba.repositories.CategoryRepository;
import br.com.futeba.services.CategoryService;
import br.com.futeba.services.impl.CategoryServiceImpl;

@RunWith(SpringRunner.class)
public class CategoryServiceImplIntegrationTest {

	@TestConfiguration
	static class CategoryServiceImplTestContextConfguration {

		@Bean
		public CategoryService categoryService() {
			return new CategoryServiceImpl();
		}
	}

	@Autowired
	private CategoryService categoryService;

	@MockBean
	private CategoryRepository categoryRepository;

	@Before
	public void setUp() {
		Category category = new Category("Futsal");
		Optional<Category> ofCategory = Optional.of(category);
		Mockito.when(categoryRepository.findByName(category.getName())).thenReturn(ofCategory);

	}

	@Test
	public void whenValidName_thenCategorySholdBeFound() {
		String nameCategory = "Futsal";
		Optional<Category> foundCategory = categoryService.findByName(nameCategory);
		assertThat(foundCategory.isPresent()).isEqualTo(true);
		assertThat(foundCategory.get().getName()).isEqualTo(nameCategory);
	}

}
