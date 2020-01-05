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
import br.com.futeba.models.Category;
import br.com.futeba.services.CategoryService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = GameDateApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class TeamCategoryControllerV1IntegrationTest {

	private static final String NAME_CATEGORY_FUTSAL = "Futsal";
	private static final String NAME_CATEGORY_SOCCER = "Soccer";
	private static final String NAME_CATEGORY_BASKETBALL = "Basketball";
	private static final String NONEXISTENT_CATEGORY_NAME = "Nonexistent";

	private static final ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MockMvc mvc;

	@MockBean
	private CategoryService service;

	private List<Category> allCategories;

	private Category futsal;
	private Category soccer;
	private Category basketball;

	@Before
	public void setUp() {
		this.futsal = new Category(1L, NAME_CATEGORY_FUTSAL);
		this.soccer = new Category(2L, NAME_CATEGORY_SOCCER);
		this.basketball = new Category(3L, NAME_CATEGORY_BASKETBALL);

		this.allCategories = Arrays.asList(futsal, soccer, basketball);
		given(service.findAll()).willReturn(allCategories);
		given(service.findByName(futsal.getName()))
				.willReturn(Optional.of(futsal));
		given(service.findById(futsal.getId())).willReturn(Optional.of(futsal));
		given(service.save(any(Category.class))).willReturn(this.futsal);
		doNothing().when(service).deleteById(1L);
		doNothing().when(service).deleteAll();
	}

	@Test
	public void givenTeamCategories_whenListAllCategories_thenStatus200()
			throws Exception {
		mvc.perform(get("/api/v1/teamCategory/")
				.contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$.[0].id", is(1)))
				.andExpect(jsonPath("$.[0].name").value(NAME_CATEGORY_FUTSAL))
				.andExpect(jsonPath("$.[1].id", is(2)))
				.andExpect(jsonPath("$.[1].name").value(NAME_CATEGORY_SOCCER))
				.andExpect(jsonPath("$.[2].id", is(3))).andExpect(
						jsonPath("$.[2].name").value(NAME_CATEGORY_BASKETBALL));

		verify(service, times(1)).findAll();
	}

	@Test
	public void givenTeamCategories_whenListCategoryById_thenStatus200()
			throws Exception {
		mvc.perform(get("/api/v1/teamCategory/1")
				.contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is(NAME_CATEGORY_FUTSAL)));

		verify(service, times(1)).findById(1L);
	}

	@Test
	public void givenTeamCategories_whenListCategoryByNonexistentId_thenStatus404()
			throws Exception {
		mvc.perform(get("/api/v1/teamCategory/5")
				.contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isNotFound());

		verify(service, times(1)).findById(5L);
	}

	@Test
	public void givenTeamCategories_whenListCategoryByName_thenStatus200()
			throws Exception {
		mvc.perform(get("/api/v1/teamCategory/list/" + NAME_CATEGORY_FUTSAL)
				.contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name").value(NAME_CATEGORY_FUTSAL));

		verify(service, times(1)).findByName(NAME_CATEGORY_FUTSAL);
	}
	@Test
	public void givenTeamCategories_whenListCategoryByNonexistentName_thenStatus404()
			throws Exception {
		mvc.perform(
				get("/api/v1/teamCategory/list/" + NONEXISTENT_CATEGORY_NAME)
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isNotFound());

		verify(service, times(1)).findByName(NONEXISTENT_CATEGORY_NAME);
	}

	@Test
	public void givenTeamCategories_whenSaveCategory_thenStatus201()
			throws Exception {
		mvc.perform(post("/api/v1/teamCategory/")
				.content(mapper.writeValueAsString(this.basketball))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isCreated())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is(NAME_CATEGORY_FUTSAL)));

		verify(service, times(1)).save(any(Category.class));;
	}

	@Test
	public void givenTeamCategories_whenSaveExistingCategory_thenStatus400()
			throws Exception {
		mvc.perform(post("/api/v1/teamCategory/")
				.content(mapper.writeValueAsString(this.futsal))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isBadRequest());

		verify(service, times(0)).save(any(Category.class));;
	}

	@Test
	public void givenTeamCategories_whenSaveCategoryWithEmptyName_thenStatus400()
			throws Exception {

		this.futsal.setName(null);

		mvc.perform(post("/api/v1/teamCategory/")
				.content(mapper.writeValueAsString(this.futsal))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isBadRequest());

		verify(service, times(0)).save(any(Category.class));
	}

	@Test
	public void givenTeamCategories_whenDeleteCategory_thenStatus200()
			throws Exception {
		mvc.perform(delete("/api/v1/teamCategory/")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		verify(service, times(1)).deleteAll();
	}

	@Test
	public void givenTeamCategories_whenDeleteCategoryById_thenStatus200()
			throws Exception {

		mvc.perform(delete("/api/v1/teamCategory/1")).andDo(print())
				.andExpect(status().isOk());

		verify(service, times(1)).deleteById(1L);
	}

}
