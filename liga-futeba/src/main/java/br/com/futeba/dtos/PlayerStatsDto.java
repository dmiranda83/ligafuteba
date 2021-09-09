package br.com.futeba.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PlayerStatsDto {
    private Object name;
    private Object goals;
    private Object assists;
    private Object goalsAverage;
    private Object frequency;
}
