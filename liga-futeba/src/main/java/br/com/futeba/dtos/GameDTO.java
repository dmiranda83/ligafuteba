package br.com.futeba.dtos;

import java.time.LocalDateTime;
import java.util.Set;

import br.com.futeba.models.GamePlayerData;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GameDTO {
    private Long id;
    private LocalDateTime dateTime;
    private Integer squad;
    private String placeId;
    private String homeTeamId;
    private Set<GamePlayerData> homeTeamData;
    private Integer homeTeamTotalGoals;
    private String awayTeamId;
    private Set<GamePlayerData> awayTeamData;
    private Integer awayTeamTotalGoals;
    private Integer points;
}
