package br.com.futeba.dtos;

import java.util.List;

import br.com.futeba.models.Team;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerDto {
    private Long id;
    private String name;
    private String positionId;
    private Long teamId;
    private List<Team> teamsId;
}
