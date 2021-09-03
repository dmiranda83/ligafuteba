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
import br.com.futeba.models.Team;
import br.com.futeba.services.TeamService;
import br.com.futeba.utils.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = GameDateApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class TeamControllerV1IntegrationTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TeamService service;

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

        this.allTeams = Arrays.asList(originais, veneza, sanRemo);

        given(service.findAll()).willReturn(allTeams);
        given(service.findByName(originais.getName()))
                .willReturn(Optional.of(originais));
        given(service.findById(originais.getId()))
                .willReturn(Optional.of(originais));
        given(service.save(any(Team.class))).willReturn(this.originais);
        doNothing().when(service).deleteById(1L);
        doNothing().when(service).deleteAll();
    }

    @Test
    public void givenTeams_whenListAllCategories_thenStatus200()
            throws Exception {
        mvc.perform(
                get("/api/v1/teams").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[0].id", is(1)))
                .andExpect(
                        jsonPath("$.[0].name").value(TestUtil.TEAM_ORIGINAIS))
                .andExpect(jsonPath("$.[1].id", is(2)))
                .andExpect(jsonPath("$.[1].name").value(TestUtil.TEAM_VENEZA))
                .andExpect(jsonPath("$.[2].id", is(3)))
                .andExpect(
                        jsonPath("$.[2].name").value(TestUtil.TEAM_SAN_REMO));

        verify(service, times(1)).findAll();
    }

    @Test
    public void givenTeams_whenListCategoryById_thenStatus200()
            throws Exception {
        mvc.perform(
                get("/api/v1/teams/1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(TestUtil.TEAM_ORIGINAIS)));

        verify(service, times(1)).findById(1L);
    }

    @Test
    public void givenTeams_whenListTeamByNonexistentId_thenStatus404()
            throws Exception {
        mvc.perform(
                get("/api/v1/teams/5").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service, times(1)).findById(5L);
    }

    @Test
    public void givenTeams_whenListTeamByName_thenStatus200() throws Exception {
        mvc.perform(get("/api/v1/teams/list/" + TestUtil.TEAM_ORIGINAIS)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(TestUtil.TEAM_ORIGINAIS));

        verify(service, times(1)).findByName(TestUtil.TEAM_ORIGINAIS);
    }

    @Test
    public void givenTeams_whenListTeamByNonexistentName_thenStatus404()
            throws Exception {
        mvc.perform(
                get("/api/v1/teams/list/" + TestUtil.NONEXISTENT_CATEGORY_NAME)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(service, times(1))
                .findByName(TestUtil.NONEXISTENT_CATEGORY_NAME);
    }

    @Test
    public void givenTeams_whenSaveTeam_thenStatus201() throws Exception {
        mvc.perform(post("/api/v1/teams")
                .content(mapper.writeValueAsString(this.veneza))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(TestUtil.TEAM_ORIGINAIS)));

        verify(service, times(1)).save(any(Team.class));
        ;
    }

    @Test
    public void givenTeams_whenSaveExistingTeam_thenStatus400()
            throws Exception {
        mvc.perform(post("/api/v1/teams")
                .content(mapper.writeValueAsString(TestUtil.TEAM_SAN_REMO))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(service, times(0)).save(any(Team.class));
        ;
    }

    @Test
    public void givenTeams_whenSaveTeamWithEmptyName_thenStatus400()
            throws Exception {

        this.originais.setName(null);

        mvc.perform(post("/api/v1/teams")
                .content(mapper.writeValueAsString(this.originais))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verify(service, times(0)).save(any(Team.class));
    }

    @Test
    public void givenTeams_whenDeleteTeam_thenStatus200() throws Exception {
        mvc.perform(delete("/api/v1/teams").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(1)).deleteAll();
    }

    @Test
    public void givenTeams_whenDeleteTeamById_thenStatus200() throws Exception {

        mvc.perform(delete("/api/v1/teams/1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(service, times(1)).deleteById(1L);
    }

}
