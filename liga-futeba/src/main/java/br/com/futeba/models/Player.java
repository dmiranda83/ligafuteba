package br.com.futeba.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Entity(name = "player")
public class Player implements Serializable {

    private static final long serialVersionUID = 2531069826794464004L;

    @Id
    @Column(name = "player_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "position_id", referencedColumnName = "position_id")
    private Position position;

    @ManyToMany(mappedBy = "players", cascade = {
            CascadeType.PERSIST,
            CascadeType.REMOVE
    }, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("players")
    private List<Team> teams;
}
