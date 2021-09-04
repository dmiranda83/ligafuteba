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
import br.com.futeba.models.Place;
import br.com.futeba.models.Team;
import br.com.futeba.utils.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = H2JpaConfig.class)
public class TeamRepositoryIntegationTest {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PlaceRepository placeRepository;

    private Team originais;
    private Team veneza;
    private Team sanRemo;
    private Category futsal;
    private Place marcelino;

    @Before
    public void setUp() {
        this.futsal = Category.builder().name(TestUtil.CATEGORY_FUTSAL).build();
        this.marcelino = Place.builder().name(TestUtil.PLACE_MARCELINO).build();

        this.sanRemo = Team.builder()
                .name(TestUtil.TEAM_SAN_REMO)
                .away(false)
                .responsibleName(TestUtil.TEAM_RESPONSABLE)
                .phoneContact1(TestUtil.TEAM_RESPONSABLE_PHONE_NUMBER)
                .build();
        this.originais = Team.builder()
                .name(TestUtil.TEAM_ORIGINAIS)
                .away(false)
                .responsibleName(TestUtil.TEAM_RESPONSABLE)
                .phoneContact1(TestUtil.TEAM_RESPONSABLE_PHONE_NUMBER)
                .build();
        this.veneza = Team.builder()
                .name(TestUtil.TEAM_VENEZA)
                .away(false)
                .responsibleName(TestUtil.TEAM_RESPONSABLE)
                .phoneContact1(TestUtil.TEAM_RESPONSABLE_PHONE_NUMBER)
                .build();
    }

    @Test
    public void givenEmptyDBWhenFindByNameThenReturnEmptyOptional() {
        Optional<Team> foundTeam = teamRepository
                .findByName(TestUtil.TEAM_VENEZA);

        assertThat(foundTeam.isPresent()).isEqualTo(false);
    }

    @Test
    public void givenCategoryInDBWhenFindByNameThenReturnOptionalWithCategory() {
        categoryRepository.save(futsal);
        placeRepository.save(marcelino);
        teamRepository.save(originais);

        Optional<Team> foundTeam = teamRepository
                .findByName(TestUtil.TEAM_ORIGINAIS);

        assertThat(foundTeam.isPresent()).isEqualTo(true);
        assertThat(foundTeam.get().getName())
                .isEqualTo(TestUtil.TEAM_ORIGINAIS);
    }

    @Test
    public void givenAllTeamsInDBWhenFindAllThenReturnOptionalWithAllTeams() {
        categoryRepository.save(futsal);
        placeRepository.save(marcelino);
        teamRepository.save(originais);
        teamRepository.save(veneza);
        teamRepository.save(sanRemo);

        List<Team> foundAllTeams = teamRepository.findAll();

        assertThat(foundAllTeams.size()).isEqualTo(3);
    }

    @Test
    public void givenTeamInDBWhenFindByIdThenReturnOptionalWithTeam() {
        categoryRepository.save(futsal);
        placeRepository.save(marcelino);
        teamRepository.save(originais);

        Optional<Team> foundTeam = teamRepository.findById(originais.getId());

        assertThat(foundTeam.isPresent()).isEqualTo(true);
        assertThat(foundTeam.get().getName())
                .isEqualTo(TestUtil.TEAM_ORIGINAIS);
    }

    @Test
    public void givenTeamInDBWhenDeleteThenReturnEmptyOptional() {
        categoryRepository.save(futsal);
        placeRepository.save(marcelino);
        teamRepository.save(originais);
        Optional<Team> teamBeforeDelete = teamRepository
                .findById(originais.getId());
        assertThat(teamBeforeDelete.isPresent()).isEqualTo(true);

        teamRepository.delete(originais);
        Optional<Team> teamAfterDelete = teamRepository
                .findById(originais.getId());
        assertThat(teamAfterDelete.isPresent()).isEqualTo(false);
    }

    @Test
    public void givenTeamInDBWhenUpdateThenReturnTeamUpdated() {
        categoryRepository.save(futsal);
        placeRepository.save(marcelino);
        teamRepository.save(originais);
        Optional<Team> teamBeforeUpdate = teamRepository
                .findById(originais.getId());
        assertThat(teamBeforeUpdate.get().getName())
                .isEqualTo(TestUtil.TEAM_ORIGINAIS);

        originais.setName(TestUtil.TEAM_RACA);

        teamRepository.saveAndFlush(originais);

        Optional<Team> teamAfterUpdate = teamRepository
                .findById(originais.getId());
        assertThat(teamAfterUpdate.get().getName())
                .isEqualTo(TestUtil.TEAM_RACA);
    }

    @After
    public void cleanUp() {
        teamRepository.deleteAll();
        categoryRepository.deleteAll();
        placeRepository.deleteAll();
    }

}
