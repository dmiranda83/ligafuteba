package br.com.futeba.models;

import java.io.Serializable;
import java.util.ArrayList;
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

	@ManyToMany(mappedBy = "players", cascade = {CascadeType.PERSIST,
			CascadeType.REMOVE}, fetch = FetchType.EAGER)
	@JsonIgnoreProperties("players")
	private List<Team> teams = new ArrayList<>();

	public Player() {
		// default constructor
	}

	public Player(Long id, @NotNull String name, Position position,
			List<Team> teams) {
		super();
		this.id = id;
		this.name = name;
		this.position = position;
		this.teams = teams;
	}

	public Player(@NotNull String name, Position position, List<Team> teams) {
		super();
		this.name = name;
		this.position = position;
		this.teams = teams;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(final Position position) {
		this.position = position;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(final List<Team> teams) {
		this.teams = teams;
	}
}
