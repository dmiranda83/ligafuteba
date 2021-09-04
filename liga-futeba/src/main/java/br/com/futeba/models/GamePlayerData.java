package br.com.futeba.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Entity(name = "gamePlayerData")
public class GamePlayerData implements Serializable {

    private static final long serialVersionUID = -8413600343702476736L;

    @Id
    @Column(name = "gol_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private Integer goals;

    @NotNull
    private Integer presence;

    @NotNull
    private Integer playedGame;

    @ManyToOne(optional = true)
    @JoinColumn(name = "player_id", referencedColumnName = "player_id")
    private Player player;

    @ManyToOne(optional = true)
    @JoinColumn(name = "team_id", referencedColumnName = "team_id")
    private Team team;
}
