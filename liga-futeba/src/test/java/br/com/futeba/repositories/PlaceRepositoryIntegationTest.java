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
import br.com.futeba.models.Place;
import br.com.futeba.utils.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = H2JpaConfig.class)
public class PlaceRepositoryIntegationTest {

    @Autowired
    private PlaceRepository placeRepository;

    private Place marcelino;
    private Place laranjeiras;
    private Place novaEra;

    @Before
    public void setUp() {
        this.marcelino = Place.builder()
                .name(TestUtil.PLACE_MARCELINO)
                .type(TestUtil.PLACE_TYPE)
                .zipCode(TestUtil.PLACE_ZIP_CODE)
                .build();

        this.laranjeiras = Place.builder()
                .name(TestUtil.PLACE_LARANJEIRAS)
                .type(TestUtil.PLACE_TYPE)
                .zipCode(TestUtil.PLACE_ZIP_CODE)
                .build();

        this.novaEra = Place.builder()
                .name(TestUtil.PLACE_NOVA_ERA)
                .type(TestUtil.PLACE_TYPE)
                .zipCode(TestUtil.PLACE_ZIP_CODE)
                .build();
    }

    @Test
    public void givenAllPlacesInDBWhenFindAllThenReturnOptionalWithAllPlaces() {
        placeRepository.save(this.marcelino);
        placeRepository.save(this.laranjeiras);
        placeRepository.save(this.novaEra);

        List<Place> foundAllPlaces = placeRepository.findAll();

        assertThat(foundAllPlaces.size()).isEqualTo(3);
    }

    @Test
    public void givenPlaceInDBWhenFindByIdThenReturnOptionalWithPlace() {
        placeRepository.save(this.marcelino);

        Optional<Place> foundPlace = placeRepository
                .findById(marcelino.getId());

        assertThat(foundPlace.isPresent()).isEqualTo(true);
        assertThat(foundPlace.get().getName())
                .isEqualTo(TestUtil.PLACE_MARCELINO);
    }

    @Test
    public void givenPlaceInDBWhenDeleteThenReturnEmptyOptional() {
        placeRepository.save(this.marcelino);

        Optional<Place> placeBeforeDelete = placeRepository
                .findById(this.marcelino.getId());
        assertThat(placeBeforeDelete.isPresent()).isEqualTo(true);

        placeRepository.delete(this.marcelino);
        Optional<Place> placeAfterDelete = placeRepository
                .findById(this.marcelino.getId());
        assertThat(placeAfterDelete.isPresent()).isEqualTo(false);
    }

    @Test
    public void givenPlaceInDBWhenDeleteByIdThenReturnEmptyOptional() {
        placeRepository.save(this.marcelino);

        Optional<Place> placeBeforeDelete = placeRepository
                .findById(marcelino.getId());
        assertThat(placeBeforeDelete.isPresent()).isEqualTo(true);

        placeRepository.deleteById(marcelino.getId());
        Optional<Place> placeAfterDelete = placeRepository
                .findById(this.marcelino.getId());
        assertThat(placeAfterDelete.isPresent()).isEqualTo(false);
    }

    @Test
    public void givenPlacesInDBWhenDeleteAllThenReturnEmptyOptional() {
        placeRepository.save(this.marcelino);
        placeRepository.save(this.laranjeiras);
        placeRepository.save(this.novaEra);

        List<Place> foundAllPlacesBeforeDelete = placeRepository.findAll();
        assertThat(foundAllPlacesBeforeDelete.isEmpty()).isEqualTo(false);

        placeRepository.deleteAll();

        List<Place> foundAllPlacesAfterDelete = placeRepository.findAll();
        assertThat(foundAllPlacesAfterDelete.isEmpty()).isEqualTo(true);
    }

    @Test
    public void givenPlaceInDBWhenUpdateThenReturnPlaceUpdated() {
        placeRepository.save(this.marcelino);

        Optional<Place> placeBeforeUpdate = placeRepository
                .findById(marcelino.getId());
        assertThat(placeBeforeUpdate.get().getName())
                .isEqualTo(TestUtil.PLACE_MARCELINO);

        marcelino.setName(TestUtil.PLACE_LARANJEIRAS);

        placeRepository.saveAndFlush(marcelino);
        Optional<Place> placeAfterUpdate = placeRepository
                .findById(marcelino.getId());
        assertThat(placeAfterUpdate.get().getName())
                .isEqualTo(TestUtil.PLACE_LARANJEIRAS);
    }

    @After
    public void cleanUp() {
        placeRepository.deleteAll();
    }

}
