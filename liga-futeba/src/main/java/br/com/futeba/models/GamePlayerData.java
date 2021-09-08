package br.com.futeba.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
@Entity
@Table(name = "gamePlayerData")
public class GamePlayerData implements Serializable {

    private static final long serialVersionUID = -8413600343702476736L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gamePlayerData_generator")
    @SequenceGenerator(name = "gamePlayerData_generator", sequenceName = "gamePlayerData_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotNull
    private Integer goals;
    @NotNull
    private Integer assists;

    @NotNull
    private Integer presence;

    @NotNull
    private Integer playedGame;

    @ManyToOne(optional = true)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

    @ManyToOne(optional = true)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;
}
