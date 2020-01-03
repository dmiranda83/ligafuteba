package br.com.futeba.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "team")
public class Team implements Serializable {

	private static final long serialVersionUID = 3457244849292203050L;

	public Team() {
		// Default constructor
	}

	@Id
	@Column(name = "team_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private String name;

	@NotNull
	private Boolean away;

	@NotNull
	private String responsibleName;

	@NotNull
	private String phoneContact1;

	private String phoneContact2;

	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "category_id")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "place_id", referencedColumnName = "place_id")
	private Place place;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "team_player", joinColumns = { @JoinColumn(name = "team_id") }, inverseJoinColumns = {
			@JoinColumn(name = "player_id") })
	@JsonIgnoreProperties("teams")
	private List<Player> players = new ArrayList<>();

	@OneToMany(mappedBy = "awayTeam")
	private List<Game> game;

	public Boolean getAway() {
		return away;
	}

	public void setAway(final Boolean away) {
		this.away = away;
	}

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getResponsibleName() {
		return responsibleName;
	}

	public void setResponsibleName(final String responsibleName) {
		this.responsibleName = responsibleName;
	}

	public String getPhoneContact1() {
		return phoneContact1;
	}

	public void setPhoneContact1(final String phoneContact1) {
		this.phoneContact1 = phoneContact1;
	}

	public String getPhoneContact2() {
		return phoneContact2;
	}

	public void setPhoneContact2(final String phoneContact2) {
		this.phoneContact2 = phoneContact2;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(final Category category) {
		this.category = category;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(final Place place) {
		this.place = place;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(final List<Player> players) {
		this.players = players;
	}
}
