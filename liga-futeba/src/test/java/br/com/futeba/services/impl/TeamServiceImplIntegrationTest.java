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

import br.com.futeba.models.Team;
import br.com.futeba.repositories.TeamRepository;
import br.com.futeba.services.TeamService;
import br.com.futeba.utils.TestUtil;

@RunWith(SpringRunner.class)
public class TeamServiceImplIntegrationTest {

    @TestConfiguration
    static class TeamServiceImplIntegrationTestContextConfguration {

        @Bean
        public TeamService teamService() {
            return new TeamServiceImpl();
        }
    }

    @Autowired
    private TeamService teamService;

    @MockBean
    private TeamRepository teamRepository;

    private List<Team> allTeams;
    private Team originais;
    private Team veneza;
    private Team sanRemo;

    @Before
    public void setUp() {
        this.sanRemo = Team.builder()
                .id(1L)
                .name(TestUtil.TEAM_SAN_REMO)
                .away(false)
                .responsibleName(TestUtil.TEAM_RESPONSABLE)
                .phoneContact1(TestUtil.TEAM_RESPONSABLE_PHONE_NUMBER)
                .build();
        this.originais = Team.builder()
                .id(2L)
                .name(TestUtil.TEAM_ORIGINAIS)
                .away(false)
                .responsibleName(TestUtil.TEAM_RESPONSABLE)
                .phoneContact1(TestUtil.TEAM_RESPONSABLE_PHONE_NUMBER)
                .build();
        this.veneza = Team.builder()
                .id(3L)
                .name(TestUtil.TEAM_VENEZA)
                .away(false)
                .responsibleName(TestUtil.TEAM_RESPONSABLE)
                .phoneContact1(TestUtil.TEAM_RESPONSABLE_PHONE_NUMBER)
                .build();

        this.allTeams = Arrays.asList(this.originais, this.veneza,
                this.sanRemo);

        Optional<Team> ofPosition = Optional.of(this.originais);
        Mockito.when(teamRepository.save(this.originais))
                .thenReturn(ofPosition.get());
        Mockito.when(teamRepository.findById(this.originais.getId()))
                .thenReturn(ofPosition);
        Mockito.when(teamRepository.findByName(this.originais.getName()))
                .thenReturn(ofPosition);
        Mockito.when(teamRepository.findAll()).thenReturn(allTeams);

    }

    @Test
    public void whenValidTeam_thenSaveTeam() {
        teamService.save(this.originais);
        assertThat(originais.getName()).isEqualTo(TestUtil.TEAM_ORIGINAIS);
    }

    @Test
    public void whenValidId_thenTeamSholdBeFound() {
        Optional<Team> foundTeam = teamService.findById(this.originais.getId());
        assertThat(foundTeam.isPresent()).isEqualTo(true);
        assertThat(foundTeam.get().getName())
                .isEqualTo(TestUtil.TEAM_ORIGINAIS);
    }

    @Test
    public void whenValidName_thenTeamSholdBeFound() {
        Optional<Team> foundTeam = teamService
                .findByName(this.originais.getName());
        assertThat(foundTeam.isPresent()).isEqualTo(true);
        assertThat(foundTeam.get().getName())
                .isEqualTo(TestUtil.TEAM_ORIGINAIS);
    }

    @Test
    public void whenListAll_thenReturnAllTeams() {
        List<Team> foundAllTeams = teamService.findAll();
        assertThat(foundAllTeams.size()).isEqualTo(3);
    }

}
