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

@Entity(name = "gamePlayerData")
public class GamePlayerData implements Serializable {

	private static final long serialVersionUID = -8413600343702476736L;

	public GamePlayerData() {
		// default constructor
	}

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

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public Integer getGoals() {
		return goals;
	}

	public void setGoals(final Integer goals) {
		this.goals = goals;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(final Player player) {
		this.player = player;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Integer getPresence() {
		return presence;
	}

	public void setPresence(Integer presence) {
		this.presence = presence;
	}

	public Integer getPlayedGame() {
		return playedGame;
	}

	public void setPlayedGame(Integer playedGame) {
		this.playedGame = playedGame;
	}

}
