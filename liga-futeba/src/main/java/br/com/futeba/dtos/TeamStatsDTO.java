package br.com.futeba.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TeamStatsDTO {

    private Object squad;
    private Object points;
    private Object scoredGoals;
    private Object concededGoals;
    private Object balanceGoals;
    private Object games;
    private Object winPercentage;
}
