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

	public Game() {
		// default contructor
	}

	public Game(Long id, Team homeTeam, Team awayTeam) {
		super();
		this.id = id;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
	}

	public Game(Team homeTeam, Team awayTeam) {
		super();
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(final LocalDate date) {
		this.date = date;
	}

	public LocalDateTime getHour() {
		return hour;
	}

	public void setHour(final LocalDateTime hour) {
		this.hour = hour;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(final Place place) {
		this.place = place;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(final Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(final Team awayTeam) {
		this.awayTeam = awayTeam;
	}

	public Set<GamePlayerData> getGamePlayerData() {
		return gamePlayerData;
	}

	public void setGamePlayerData(final Set<GamePlayerData> gamePlayerData) {
		this.gamePlayerData = gamePlayerData;
	}

	public Set<GamePlayerData> getAwayGoals() {
		return awayGoals;
	}

	public void setAwayGoals(final Set<GamePlayerData> awayGoals) {
		this.awayGoals = awayGoals;
	}

	public Integer getSquad() {
		return squad;
	}

	public void setSquad(Integer squad) {
		this.squad = squad;
	}

	public Integer getHomeTeamTotalGoals() {
		return homeTeamTotalGoals;
	}

	public void setHomeTeamTotalGoals(Integer homeTeamTotalGoals) {
		this.homeTeamTotalGoals = homeTeamTotalGoals;
	}

	public Integer getAwayTeamTotalGoals() {
		return awayTeamTotalGoals;
	}

	public void setAwayTeamTotalGoals(Integer awayTeamTotalGoals) {
		this.awayTeamTotalGoals = awayTeamTotalGoals;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Set<Assist> getHomeAssists() {
		return homeAssists;
	}

	public void setHomeAssists(Set<Assist> homeAssists) {
		this.homeAssists = homeAssists;
	}

	public Set<Assist> getAwayAssists() {
		return awayAssists;
	}

	public void setAwayAssists(Set<Assist> awayAssists) {
		this.awayAssists = awayAssists;
	}

	public Set<GamePlayerData> homeGoals() {
		return homeGoals;
	}

	public void setGolsMandante(Set<GamePlayerData> homeGoals) {
		this.homeGoals = homeGoals;
	}
}
