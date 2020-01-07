package br.com.futeba.services.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
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
import br.com.futeba.utils.TestUtil;

@RunWith(SpringRunner.class)
public class GameServiceImplIntegrationTest {

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

	private List<Category> allCategories;
	private Category futsal;
	private Category soccer;
	private Category basketball;

	@Before
	public void setUp() {
		this.futsal = new Category(1L, TestUtil.NAME_CATEGORY_FUTSAL);
		this.soccer = new Category(2L, TestUtil.NAME_CATEGORY_FUTSAL);
		this.basketball = new Category(3L, TestUtil.NAME_CATEGORY_FUTSAL);

		this.allCategories = Arrays.asList(futsal, soccer, basketball);

		Optional<Category> ofCategory = Optional.of(this.futsal);
		Mockito.when(categoryRepository.findByName(this.futsal.getName()))
				.thenReturn(ofCategory);
		Mockito.when(categoryRepository.save(this.futsal))
				.thenReturn(ofCategory.get());
		Mockito.when(categoryRepository.findById(this.futsal.getId()))
				.thenReturn(ofCategory);
		Mockito.when(categoryRepository.findAll()).thenReturn(allCategories);

	}

	@Test
	public void whenValidCategory_thenSaveCategory() {
		Category futsal = categoryService.save(this.futsal);
		assertThat(futsal.getName()).isEqualTo(TestUtil.NAME_CATEGORY_FUTSAL);
	}

	@Test
	public void whenValidId_thenCategorySholdBeFound() {
		Optional<Category> foundCategory = categoryService
				.findById(this.futsal.getId());
		assertThat(foundCategory.isPresent()).isEqualTo(true);
		assertThat(foundCategory.get().getName())
				.isEqualTo(TestUtil.NAME_CATEGORY_FUTSAL);
	}

	@Test
	public void whenValidName_thenCategorySholdBeFound() {
		Optional<Category> foundCategory = categoryService
				.findByName(TestUtil.NAME_CATEGORY_FUTSAL);
		assertThat(foundCategory.isPresent()).isEqualTo(true);
		assertThat(foundCategory.get().getName())
				.isEqualTo(TestUtil.NAME_CATEGORY_FUTSAL);
	}

	@Test
	public void whenListAll_thenReturnAllCategories() {
		List<Category> foundAllCategories = categoryService.findAll();
		assertThat(foundAllCategories.size()).isEqualTo(3);
	}

}
