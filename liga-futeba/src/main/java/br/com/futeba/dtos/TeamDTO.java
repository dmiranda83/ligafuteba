package br.com.futeba.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TeamDTO {
    private Long id;
    private String name;
    private Boolean away;
    private String responsibleName;
    private String phoneContact1;
    private String phoneContact2;
    private String categoryId;
    private String placeID;
    private String playerId;
    private PlayerDto player;
}
