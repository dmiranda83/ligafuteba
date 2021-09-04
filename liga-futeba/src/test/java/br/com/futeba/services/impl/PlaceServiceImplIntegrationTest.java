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

import br.com.futeba.models.Place;
import br.com.futeba.repositories.PlaceRepository;
import br.com.futeba.services.PlaceService;
import br.com.futeba.utils.TestUtil;

@RunWith(SpringRunner.class)
public class PlaceServiceImplIntegrationTest {

    @TestConfiguration
    static class PlaceServiceImplTestContextConfguration {

        @Bean
        public PlaceService placeService() {
            return new PlaceServiceImpl();
        }
    }

    @Autowired
    private PlaceService placeService;

    @MockBean
    private PlaceRepository placeRepository;

    private List<Place> allPlaces;
    private Place marcelino;
    private Place laranjeiras;
    private Place novaEra;

    @Before
    public void setUp() {
        this.marcelino = Place.builder()
                .id(1L)
                .name(TestUtil.PLACE_MARCELINO)
                .type(TestUtil.PLACE_TYPE)
                .zipCode(TestUtil.PLACE_ZIP_CODE)
                .build();

        this.laranjeiras = Place.builder()
                .id(2L)
                .name(TestUtil.PLACE_LARANJEIRAS)
                .type(TestUtil.PLACE_TYPE)
                .zipCode(TestUtil.PLACE_ZIP_CODE)
                .build();

        this.novaEra = Place.builder()
                .id(3L)
                .name(TestUtil.PLACE_NOVA_ERA)
                .type(TestUtil.PLACE_TYPE)
                .zipCode(TestUtil.PLACE_ZIP_CODE)
                .build();

        this.allPlaces = Arrays.asList(marcelino, laranjeiras, novaEra);

        Optional<Place> ofPlace = Optional.of(this.marcelino);
        Mockito.when(placeRepository.findByName(this.marcelino.getName()))
                .thenReturn(ofPlace);
        Mockito.when(placeRepository.save(this.marcelino))
                .thenReturn(ofPlace.get());
        Mockito.when(placeRepository.findById(this.marcelino.getId()))
                .thenReturn(ofPlace);
        Mockito.when(placeRepository.findAll()).thenReturn(allPlaces);

    }

    @Test
    public void whenValidPlace_thenSavePlace() {
        Place marcelino = placeService.save(this.marcelino);
        assertThat(marcelino.getName()).isEqualTo(TestUtil.PLACE_MARCELINO);
    }

    @Test
    public void whenValidId_thenPlaceSholdBeFound() {
        Optional<Place> foundPlace = placeService
                .findById(this.marcelino.getId());
        assertThat(foundPlace.isPresent()).isEqualTo(true);
        assertThat(foundPlace.get().getName())
                .isEqualTo(TestUtil.PLACE_MARCELINO);
    }

    @Test
    public void whenValidName_thenPlaceSholdBeFound() {
        Optional<Place> foundPlace = placeService
                .findByName(TestUtil.PLACE_MARCELINO);
        assertThat(foundPlace.isPresent()).isEqualTo(true);
        assertThat(foundPlace.get().getName())
                .isEqualTo(TestUtil.PLACE_MARCELINO);
    }

    @Test
    public void whenListAll_thenReturnAllCategories() {
        List<Place> foundAllCategories = placeService.findAll();
        assertThat(foundAllCategories.size()).isEqualTo(3);
    }

}
