package br.com.futeba.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;

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
@Entity
@Table(name = "place")
public class Place implements Serializable {

    private static final long serialVersionUID = 2062908763035875940L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "place_generator")
    @SequenceGenerator(name = "place_generator", sequenceName = "place_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotNull
    private String name;

    private String type;
    private String address;
    private String city;
    private String neighborhood;
    private String zipCode;

    public Boolean isPlaceWithoutZipCode() {
        return StringUtils.isEmpty(this.zipCode);
    }
}
