package br.com.futeba.dtos;

import java.util.List;

import br.com.futeba.models.Team;

public class PlayerDto {
    private Long id;
    private String name;
    private String positionId;
    private Long teamId;
    private List<Team> teamsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public List<Team> getTeamsId() {
        return teamsId;
    }

    public void setTeamsId(List<Team> teamsId) {
        this.teamsId = teamsId;
    }
}
