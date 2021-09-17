package br.com.futeba.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name = "week")
public class Week implements Serializable {

    private static final long serialVersionUID = 7894516288691590439L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "week_generator")
    @SequenceGenerator(name = "week_generator", sequenceName = "week_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotNull
    private String name;

    @ManyToMany(mappedBy = "weeks")
    @JsonBackReference
    private Set<Team> teams;
}
