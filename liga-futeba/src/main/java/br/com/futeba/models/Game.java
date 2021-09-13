package br.com.futeba.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
@Table(name = "game")
public class Game implements Serializable {

    private static final long serialVersionUID = 2531069826794464004L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_generator")
    @SequenceGenerator(name = "game_generator", sequenceName = "game_seq", allocationSize = 1)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private LocalDateTime dateTime;
    private Integer squad;
    @ManyToOne
    @JoinColumn(name = "place_id", referencedColumnName = "id")
    private Place place;
    @ManyToOne(optional = true)
    @JoinColumn(name = "home_team_id", referencedColumnName = "id")
    private Team homeTeam;
    // @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // private Set<GamePlayerData> homeTeamData;
    @ManyToOne(optional = true)
    @JoinColumn(name = "away_team_id", referencedColumnName = "id")
    private Team awayTeam;
    // @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // private Set<GamePlayerData> awayTeamData;
    private Integer homeTeamTotalGoals;
    private Integer awayTeamTotalGoals;
    private Integer points;
}
