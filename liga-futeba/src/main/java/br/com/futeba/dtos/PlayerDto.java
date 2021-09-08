package br.com.futeba.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PlayerDto {
    private Long id;
    private String name;
    private String positionId;
    private Long teamId;
}
