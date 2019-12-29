package br.com.futeba.repositorys;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.futeba.models.Category;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	public void testSaveCategory() {
		Category category = new Category("Futsal");
		categoryRepository.save(category);
		
		Category foundCategory = categoryRepository.findByName("Futsal");
		
		assertNotNull(category);
		assertEquals(foundCategory.getName(), category.getName());
	}
	
	@Test
    public void testDeleteCategory() {
        Category category = new Category("Basquete");
        categoryRepository.save(category);
        categoryRepository.delete(category);
    }
	
	 @Test
    public void findAllCategories() {
		Category category = new Category("Futebol de Campo");
		categoryRepository.save(category);
        assertNotNull(categoryRepository.findAll());
	    }

    @Test
    public void deletByCategoryIdTest() {
    	Category category = new Category("Voleibol");
    	Category categoryDelete = categoryRepository.save(category);
    	categoryRepository.deleteById(categoryDelete.getId());
    }

}
