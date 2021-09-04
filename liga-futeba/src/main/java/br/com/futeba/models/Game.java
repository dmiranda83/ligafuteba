package br.com.futeba.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
@Entity(name = "game")
public class Game implements Serializable {

    private static final long serialVersionUID = 2531069826794464004L;

    @Id
    @Column(name = "game_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate date;
    private LocalDateTime hour;
    private Integer squad;
    @ManyToOne
    @JoinColumn(name = "place_id", referencedColumnName = "place_id")
    private Place place;
    @ManyToOne(optional = true)
    @JoinColumn(name = "home_team_id", referencedColumnName = "team_id")
    private Team homeTeam;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<GamePlayerData> gamePlayerData;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<GamePlayerData> homeGoals;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Assist> homeAssists;
    @ManyToOne(optional = true)
    @JoinColumn(name = "away_team_id", referencedColumnName = "team_id")
    private Team awayTeam;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<GamePlayerData> awayGoals;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Assist> awayAssists;
    private Integer homeTeamTotalGoals;
    private Integer awayTeamTotalGoals;
    private Integer points;
}
