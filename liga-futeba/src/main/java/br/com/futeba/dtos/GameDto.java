package br.com.futeba.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;
    private Integer squad;
    private String placeId;
    private String homeTeamId;
    // private Set<GamePlayerData> homeTeamData;
    private Integer homeTeamTotalGoals;
    private String awayTeamId;
    // private Set<GamePlayerData> awayTeamData;
    private Integer awayTeamTotalGoals;
    private Integer points;
}
