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

import br.com.futeba.models.Position;
import br.com.futeba.repositories.PositionRepository;
import br.com.futeba.services.PositionService;
import br.com.futeba.utils.TestUtil;

@RunWith(SpringRunner.class)
public class PositionServiceImplIntegrationTest {

	@TestConfiguration
	static class PositionServiceImplIntegrationTestContextConfguration {

		@Bean
		public PositionService positionService() {
			return new PositionServiceImpl();
		}
	}

	@Autowired
	private PositionService positionService;

	@MockBean
	private PositionRepository positionRepository;

	private List<Position> allPositions;
	private Position alaEsquerdo;
	private Position alaDireito;
	private Position fixo;

	@Before
	public void setUp() {
		this.alaEsquerdo = new Position(1L, TestUtil.POSITION_ALA_ESQUERDA);
		this.alaDireito = new Position(2L, TestUtil.POSITION_ALA_DIREITA);
		this.fixo = new Position(3L, TestUtil.POSITION_FIXO);

		this.allPositions = Arrays.asList(this.alaEsquerdo, this.alaDireito,
				this.fixo);

		Optional<Position> ofPosition = Optional.of(this.alaEsquerdo);
		Mockito.when(positionRepository.save(this.alaEsquerdo))
				.thenReturn(ofPosition.get());
		Mockito.when(positionRepository.findById(this.alaEsquerdo.getId()))
				.thenReturn(ofPosition);
		Mockito.when(positionRepository.findByName(this.alaEsquerdo.getName()))
				.thenReturn(ofPosition);
		Mockito.when(positionRepository.findAll()).thenReturn(allPositions);

	}

	@Test
	public void whenValidPosition_thenSavePosition() {
		positionService.save(this.alaEsquerdo);
		assertThat(alaEsquerdo.getName())
				.isEqualTo(TestUtil.POSITION_ALA_ESQUERDA);
	}

	@Test
	public void whenValidId_thenPositionSholdBeFound() {
		Optional<Position> foundPosition = positionService
				.findById(this.alaEsquerdo.getId());
		assertThat(foundPosition.isPresent()).isEqualTo(true);
		assertThat(foundPosition.get().getName())
				.isEqualTo(TestUtil.POSITION_ALA_ESQUERDA);
	}

	@Test
	public void whenValidName_thenPositionSholdBeFound() {
		Optional<Position> foundPosition = positionService
				.findByName(this.alaEsquerdo.getName());
		assertThat(foundPosition.isPresent()).isEqualTo(true);
		assertThat(foundPosition.get().getName())
				.isEqualTo(TestUtil.POSITION_ALA_ESQUERDA);
	}

	@Test
	public void whenListAll_thenReturnAllPositions() {
		List<Position> foundAllPositions = positionService.findAll();
		assertThat(foundAllPositions.size()).isEqualTo(3);
	}

}
