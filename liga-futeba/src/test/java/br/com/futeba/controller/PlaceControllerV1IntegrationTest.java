package br.com.futeba.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;

import br.com.futeba.GameDateApplication;
import br.com.futeba.models.Place;
import br.com.futeba.services.PlaceService;
import br.com.futeba.utils.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = GameDateApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class PlaceControllerV1IntegrationTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PlaceService service;

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
        given(service.findAll()).willReturn(allPlaces);
        given(service.findByName(marcelino.getName()))
                .willReturn(Optional.of(marcelino));
        given(service.findById(marcelino.getId()))
                .willReturn(Optional.of(marcelino));
        given(service.save(any(Place.class))).willReturn(this.laranjeiras);
        doNothing().when(service).deleteById(1L);
        doNothing().when(service).deleteAll();
    }

    @Test
    public void givenPlaces_whenListAllPlaces_thenStatus200() throws Exception {
        mvc.perform(
                get("/api/v1/places").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[0].id", is(1)))
                .andExpect(
                        jsonPath("$.[0].name").value(TestUtil.PLACE_MARCELINO))
                .andExpect(jsonPath("$.[1].id", is(2)))
                .andExpect(jsonPath("$.[1].name")
                        .value(TestUtil.PLACE_LARANJEIRAS))
                .andExpect(jsonPath("$.[2].id", is(3)))
                .andExpect(
                        jsonPath("$.[2].name").value(TestUtil.PLACE_NOVA_ERA));

        verify(service, times(1)).findAll();
    }

    @Test
    public void givenPlaces_whenListPlaceById_thenStatus200() throws Exception {
        mvc.perform(
                get("/api/v1/places/1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(TestUtil.PLACE_MARCELINO)));

        verify(service, times(1)).findById(1L);
    }

    @Test
    public void givenPlaces_whenListPlaceByNonexistentId_thenStatus404()
            throws Exception {
        mvc.perform(
                get("/api/v1/places/5").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service, times(1)).findById(5L);
    }

    @Test
    public void givenPlaces_whenListPlaceByName_thenStatus200()
            throws Exception {
        mvc.perform(get("/api/v1/places/list/" + TestUtil.PLACE_MARCELINO)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(TestUtil.PLACE_MARCELINO));

        verify(service, times(1)).findByName(TestUtil.PLACE_MARCELINO);
    }

    @Test
    public void givenPlaces_whenListPlaceByNonexistentName_thenStatus404()
            throws Exception {
        mvc.perform(
                get("/api/v1/places/list/" + TestUtil.NONEXISTENT_CATEGORY_NAME)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service, times(1))
                .findByName(TestUtil.NONEXISTENT_CATEGORY_NAME);
    }

    @Test
    public void givenPlaces_whenSavePlace_thenStatus201() throws Exception {
        mvc.perform(post("/api/v1/places")
                .content(mapper.writeValueAsString(this.laranjeiras))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.name", is(TestUtil.PLACE_LARANJEIRAS)));

        verify(service, times(1)).save(any(Place.class));
        ;
    }

    @Test
    public void givenPlaces_whenSaveExistingPlace_thenStatus400()
            throws Exception {
        mvc.perform(post("/api/v1/places")
                .content(mapper.writeValueAsString(this.marcelino))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(service, times(0)).save(any(Place.class));
        ;
    }

    @Test
    public void givenPlaces_whenSavePlaceWithEmptyName_thenStatus400()
            throws Exception {

        this.marcelino.setName(null);

        mvc.perform(post("/api/v1/places")
                .content(mapper.writeValueAsString(this.marcelino))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(service, times(0)).save(any(Place.class));
    }

    @Test
    public void givenPlaces_whenDeletePlace_thenStatus200() throws Exception {
        mvc.perform(delete("/api/v1/places").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(1)).deleteAll();
    }

    @Test
    public void givenPlaces_whenDeletePlaceById_thenStatus200()
            throws Exception {

        mvc.perform(delete("/api/v1/places/1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(service, times(1)).deleteById(1L);
    }

}
