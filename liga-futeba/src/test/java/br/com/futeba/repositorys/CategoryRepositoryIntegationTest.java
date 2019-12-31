package br.com.futeba.repositorys;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.futeba.config.H2JpaConfig;
import br.com.futeba.models.Category;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = H2JpaConfig.class)
public class CategoryRepositoryIntegationTest {

	private static final String CATEGORY_NAME = "Futsal";

	@Autowired
	private CategoryRepository categoryRepository;

	private Category futsal;
	private Category futebolCampo;
	private Category basquete;

	@Before
	public void setUp() {
		this.futsal = new Category("Futsal");
		this.futebolCampo = new Category("Futebal de Campo");
		this.basquete = new Category("Basquete");
	}

	@Test
	public void givenEmptyDBWhenFindByNameThenReturnEmptyOptional() {
		Optional<Category> foundCategory = categoryRepository.findByName(CATEGORY_NAME);

		assertThat(foundCategory.isPresent()).isEqualTo(false);
	}

	@Test
	public void givenCategoryInDBWhenFindByNameThenReturnOptionalWithCategory() {
		categoryRepository.save(futsal);

		Optional<Category> foundCategory = categoryRepository.findByName(CATEGORY_NAME);

		assertThat(foundCategory.isPresent()).isEqualTo(true);
		assertThat(foundCategory.get().getName()).isEqualTo(CATEGORY_NAME);
	}

	@Test
	public void givenAllCategoriesInDBWhenFindAllThenReturnOptionalWithAllCategories() {
		categoryRepository.save(futsal);
		categoryRepository.save(futebolCampo);
		categoryRepository.save(basquete);

		List<Category> foundAllCategories = categoryRepository.findAll();

		assertThat(foundAllCategories.size()).isEqualTo(3);
	}

//	@Test
//    public void testDeleteCategory() {
//        Category category = new Category("Basquete");
//        categoryRepository.save(category);
//        categoryRepository.delete(category);
//        
//        Optional<Category> foundCategory = categoryRepository.findById(category.getId());
//        
//        assertTrue(!foundCategory.isPresent());
//    }
//	
//	@Test
//    public void findAllCategories() {
//		Category category = new Category("Futebol de Campo");
//		categoryRepository.save(category);
//        List<Category> findAll = categoryRepository.findAll();
//		assertNotNull(findAll);
//    }
//
//	@Test
//	public void findByCategoryId() {
//		Category category = new Category("Futebol de Campo");
//		categoryRepository.save(category);
//		Optional<Category> foundCategory = categoryRepository.findById(category.getId());
//		assertNotNull(category);
//		assertEquals(category.getName(), foundCategory.get().getName());
//	}
//	
//	@Test
//	public void updateCategory() {
//		Category category = new Category("Voleibol");
//		categoryRepository.save(category);		
//		
//		Optional<Category> foundCategory = categoryRepository.findById(category.getId());
//		foundCategory.get().setName("Futsal");
//		Category savedCategory = categoryRepository.saveAndFlush(foundCategory.get());
//		
//		assertNotNull(category);
//		assertEquals(foundCategory.get().getName(), savedCategory.getName());
//	}
//
//    @Test
//    public void deleteByCategoryIdTest() {
//    	Category category = new Category("Voleibol");
//    	Category categoryDelete = categoryRepository.save(category);
//    	categoryRepository.deleteById(categoryDelete.getId());
//    }
//    
//    @Test
//    public void deleteAllCategoriesTest() {
//    	Category voleibol = new Category("Voleibol");
//    	Category futsal = new Category("Futsal");
//    	categoryRepository.save(voleibol);
//    	categoryRepository.save(futsal);
//    	
//    	categoryRepository.deleteAll();
//    	
//    	List<Category> allCategories = categoryRepository.findAll();
//    	
//    	assertTrue(allCategories.isEmpty());
//    }

	@After
	public void cleanUp() {
		categoryRepository.deleteAll();
	}

}
