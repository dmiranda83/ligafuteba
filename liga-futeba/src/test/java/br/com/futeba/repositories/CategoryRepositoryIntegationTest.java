package br.com.futeba.repositories;

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
import br.com.futeba.utils.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = H2JpaConfig.class)
public class CategoryRepositoryIntegationTest {

    @Autowired
    private CategoryRepository categoryRepository;

    private Category futsal;
    private Category soccer;
    private Category basketball;

    @Before
    public void setUp() {
        this.futsal = Category.builder()
                .name(TestUtil.CATEGORY_FUTSAL)
                .build();
        this.soccer = Category.builder()
                .name(TestUtil.CATEGORY_SOCCER)
                .build();
        this.basketball = Category.builder()
                .name(TestUtil.CATEGORY_BASKETBALL)
                .build();
    }

    @Test
    public void givenEmptyDBWhenFindByNameThenReturnEmptyOptional() {
        Optional<Category> foundCategory = categoryRepository
                .findByName(TestUtil.CATEGORY_FUTSAL);

        assertThat(foundCategory.isPresent()).isEqualTo(false);
    }

    @Test
    public void givenCategoryInDBWhenFindByNameThenReturnOptionalWithCategory() {
        categoryRepository.save(this.futsal);

        Optional<Category> foundCategory = categoryRepository
                .findByName(TestUtil.CATEGORY_FUTSAL);

        assertThat(foundCategory.isPresent()).isEqualTo(true);
        assertThat(foundCategory.get().getName())
                .isEqualTo(TestUtil.CATEGORY_FUTSAL);
    }

    @Test
    public void givenAllCategoriesInDBWhenFindAllThenReturnOptionalWithAllCategories() {
        categoryRepository.save(futsal);
        categoryRepository.save(soccer);
        categoryRepository.save(basketball);

        List<Category> foundAllCategories = categoryRepository.findAll();

        assertThat(foundAllCategories.size()).isEqualTo(3);
    }

    @Test
    public void givenCategoryInDBWhenFindByIdThenReturnOptionalWithCategory() {
        categoryRepository.save(futsal);

        Optional<Category> foundCategory = categoryRepository
                .findById(futsal.getId());

        assertThat(foundCategory.isPresent()).isEqualTo(true);
        assertThat(foundCategory.get().getName())
                .isEqualTo(TestUtil.CATEGORY_FUTSAL);
    }

    @Test
    public void givenCategoryInDBWhenDeleteThenReturnEmptyOptional() {
        categoryRepository.save(futsal);
        Optional<Category> categoryBeforeDelete = categoryRepository
                .findById(futsal.getId());
        assertThat(categoryBeforeDelete.isPresent()).isEqualTo(true);

        categoryRepository.delete(futsal);
        Optional<Category> categoryAfterDelete = categoryRepository
                .findById(futsal.getId());
        assertThat(categoryAfterDelete.isPresent()).isEqualTo(false);
    }

    @Test
    public void givenCategoryInDBWhenDeleteByIdThenReturnEmptyOptional() {
        categoryRepository.save(futsal);
        Optional<Category> categoryBeforeDelete = categoryRepository
                .findById(futsal.getId());
        assertThat(categoryBeforeDelete.isPresent()).isEqualTo(true);

        categoryRepository.deleteById(futsal.getId());
        Optional<Category> categoryAfterDelete = categoryRepository
                .findById(futsal.getId());
        assertThat(categoryAfterDelete.isPresent()).isEqualTo(false);
    }

    @Test
    public void givenCategoriesInDBWhenDeleteAllThenReturnEmptyOptional() {
        categoryRepository.save(futsal);
        categoryRepository.save(soccer);
        categoryRepository.save(basketball);

        List<Category> foundAllCategoriesBeforeDelete = categoryRepository
                .findAll();
        assertThat(foundAllCategoriesBeforeDelete.isEmpty()).isEqualTo(false);

        categoryRepository.deleteAll();

        List<Category> foundAllCategoriesAfterDelete = categoryRepository
                .findAll();
        assertThat(foundAllCategoriesAfterDelete.isEmpty()).isEqualTo(true);
    }

    @Test
    public void givenCategoryInDBWhenUpdateThenReturnCategoryUpdated() {
        categoryRepository.save(futsal);
        Optional<Category> categoryBeforeUpdate = categoryRepository
                .findById(futsal.getId());
        assertThat(categoryBeforeUpdate.get().getName())
                .isEqualTo(TestUtil.CATEGORY_FUTSAL);

        futsal.setName(TestUtil.CATEGORY_SOCCER);
        categoryRepository.saveAndFlush(futsal);
        Optional<Category> categoryAfterUpdate = categoryRepository
                .findById(futsal.getId());
        assertThat(categoryAfterUpdate.get().getName())
                .isEqualTo(TestUtil.CATEGORY_SOCCER);
    }

    @After
    public void cleanUp() {
        categoryRepository.deleteAll();
    }

}
