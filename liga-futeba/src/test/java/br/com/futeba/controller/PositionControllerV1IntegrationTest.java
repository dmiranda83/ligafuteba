package br.com.futeba.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import br.com.futeba.GameDateApplication;
import br.com.futeba.models.Position;
import br.com.futeba.services.PositionService;
import br.com.futeba.utils.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = GameDateApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class PositionControllerV1IntegrationTest {

	private static final ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MockMvc mvc;

	@MockBean
	private PositionService service;

	private List<Position> allPositions;

	private Position alaEsquerdo;
	private Position alaDireito;
	private Position fixo;

	@Before
	public void setUp() {
		this.alaEsquerdo = new Position(1L, TestUtil.POSITION_ALA_ESQUERDA);
		this.alaDireito = new Position(2L, TestUtil.POSITION_ALA_DIREITA);
		this.fixo = new Position(3L, TestUtil.POSITION_FIXO);

		this.allPositions = Arrays.asList(alaEsquerdo, alaDireito, fixo);
		given(service.findAll()).willReturn(allPositions);
		given(service.findByName(alaEsquerdo.getName()))
				.willReturn(Optional.of(alaEsquerdo));
		given(service.findById(alaEsquerdo.getId()))
				.willReturn(Optional.of(alaEsquerdo));
		given(service.save(any(Position.class))).willReturn(this.alaDireito);
		doNothing().when(service).deleteById(1L);
		doNothing().when(service).deleteAll();
	}

	@Test
	public void givenPositions_whenListPositionById_thenStatus200()
			throws Exception {
		mvc.perform(get("/api/v1/playerPositions/1")
				.contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", is(1))).andExpect(
						jsonPath("$.name", is(TestUtil.POSITION_ALA_ESQUERDA)));

		verify(service, times(1)).findById(1L);
	}

	@Test
	public void givenPositions_whenListPositionByNonexistentId_thenStatus404()
			throws Exception {
		mvc.perform(get("/api/v1/playerPositions/5")
				.contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isNotFound());

		verify(service, times(1)).findById(5L);
	}

	@Test
	public void givenPositions_whenListPositionByName_thenStatus200()
			throws Exception {
		mvc.perform(get("/api/v1/playerPositions/list/"
				+ TestUtil.POSITION_ALA_ESQUERDA)
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name")
						.value(TestUtil.POSITION_ALA_ESQUERDA));

		verify(service, times(1)).findByName(TestUtil.POSITION_ALA_ESQUERDA);
	}
	@Test
	public void givenPositions_whenListPositionByNonexistentName_thenStatus404()
			throws Exception {
		mvc.perform(get("/api/v1/playerPositions/list/"
				+ TestUtil.NONEXISTENT_CATEGORY_NAME)
						.contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isNotFound());

		verify(service, times(1))
				.findByName(TestUtil.NONEXISTENT_CATEGORY_NAME);
	}

	@Test
	public void givenPositions_whenDeletePosition_thenStatus200()
			throws Exception {
		mvc.perform(delete("/api/v1/playerPositions")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

		verify(service, times(1)).deleteAll();
	}

	@Test
	public void givenPositions_whenDeletePositionById_thenStatus200()
			throws Exception {

		mvc.perform(delete("/api/v1/playerPositions/1")).andDo(print())
				.andExpect(status().isOk());

		verify(service, times(1)).deleteById(1L);
	}

}
