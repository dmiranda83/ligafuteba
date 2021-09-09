package br.com.futeba.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PlaceDto {
    private Long id;
    private String name;
    private String type;
    private String address;
    private String city;
    private String neighborhood;
    private String zipCode;
}
