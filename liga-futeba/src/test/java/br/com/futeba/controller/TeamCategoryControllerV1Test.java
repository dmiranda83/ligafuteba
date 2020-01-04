package br.com.futeba.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.futeba.controllers.TeamCategoryControllerV1;
import br.com.futeba.models.Category;
import br.com.futeba.services.CategoryService;

@RunWith(SpringRunner.class)
@WebMvcTest(TeamCategoryControllerV1.class)
public class TeamCategoryControllerV1Test {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private CategoryService service;

	@Test
	public void givenTeamCategories_whenGetCategories_thenReturnJsonArray() throws Exception {
		Category category = new Category("Futsal");

		List<Category> allCategories = Arrays.asList(category);

		given(service.findAll()).willReturn(allCategories);

		mvc.perform(get("/api/v1/teamCategory/listAll").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$").exists())
				.andExpect(jsonPath("$.[0].name").value(category.getName()));
	}

}
