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

@Entity(name = "assist")
public class Assist implements Serializable {


	private static final long serialVersionUID = -3633732656049254149L;

	public Assist() {
		//default constructor
    }

    @Id
    @Column(name = "assist_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private Integer qtdAssist;

    @ManyToOne(optional = true)
    @JoinColumn(name = "player_id", referencedColumnName = "player_id")
    private Player player;
    
    @ManyToOne(optional = true)
    @JoinColumn(name = "team_id", referencedColumnName = "team_id")
    private Team team;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(final Player player) {
        this.player = player;
    }

	public Integer getQtdAssist() {
		return qtdAssist;
	}

	public void setQtdAssist(Integer qtdAssist) {
		this.qtdAssist = qtdAssist;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
}
