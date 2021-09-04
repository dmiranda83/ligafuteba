package br.com.futeba.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

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
@Entity(name = "place")
public class Place implements Serializable {

    private static final long serialVersionUID = 2062908763035875940L;

    @Id
    @Column(name = "place_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    private String type;
    private String address;
    private String city;
    private String neighborhood;
    private String zipCode;

    public Boolean isPlaceWithoutZipCode() {
        return this.zipCode.isEmpty();
    }
}
